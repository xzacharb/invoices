package com.xzacharb.coresvc.service;

import com.xzacharb.coresvc.model.dto.CityCount;
import com.xzacharb.coresvc.model.dto.RuleCount;
import com.xzacharb.coresvc.model.dao.Process;
import com.xzacharb.coresvc.model.dto.InvoiceData;
import com.xzacharb.coresvc.model.dto.ManagementPerson;
import com.xzacharb.coresvc.model.dao.*;
import com.xzacharb.coresvc.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoreDbService {
    // static String QUERY_ISSUES_PER_CITY = "select distinct a from InvoiceDao a join EvaluatedResult r on r.invoiceDao = a.id where a.city = ?1";
    private static String QUERY_RULES_PER_CITY = "select new com.xzacharb.coresvc.model.dto.RuleCount(r.evaluator_name,a.city.city_name,a.city.city_short,r.description,r.evaluatorNameShort, count(a)) from InvoiceDao a join EvaluatedResult r on r.invoiceDao = a.id where a.city = ?1 group by r.evaluator_name, r.description, r.evaluatorNameShort order by count(r) desc";
    private static String QUERY_INVOICE_PER_RULE_CITY = "select distinct a from InvoiceDao a join EvaluatedResult r on r.invoiceDao = a.id where a.city.city_short = ?1 and r.evaluatorNameShort= ?2";
    private static String QUERY_ISSUES_PER_CITY_COUNT = "select new com.xzacharb.coresvc.model.dto.CityCount(a.city.city_name,a.city.city_short, count(a)) from InvoiceDao a join EvaluatedResult r on r.invoiceDao = a.id group by a.city order by count(a) desc";

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    CommunicationService commSVC;

    @Autowired
    public LegalFormRepo legalFormRepo;

    @Autowired
    public RoleRepo roleRepo;

    @Autowired
    public ContractorRepo contractorRepo;

    @Autowired
    public CityRepo cityRepo;

    @Autowired
    public ProcessesRepo processesRepo;

    @Autowired
    public ManagementPersonRepo managementPersonRepo;

    @Autowired
    public ManagementTypeRepo managementTypeRepo;

    @Autowired
    public InvoicesRepo invoicesRepo;

    @Autowired
    public EvaluatedResultRepo evaluatedResultRepo;

    @PersistenceContext
    private EntityManager em;

    /**
     * Get count of issued invoices per city
     *
     * @return
     */
    public final List<CityCount> getCitiesCount() {
        List<CityCount> cityCounts = new ArrayList<>();
        for (City city : cityRepo.findAll()) {
            cityCounts.add(new CityCount(city.getCity_name(), city.getCity_short(), 0l));
        }
        return cityCounts;
    }

    /**
     * Get count of issued invoices per city
     *
     * @return
     */
    public final List<CityCount> getAlertCitiesCount() {
        TypedQuery query = em.createQuery(QUERY_ISSUES_PER_CITY_COUNT, CityCount.class);
        return query.getResultList();
    }

    public final List<Process> getAllProcesses() {
        List<Process> result = new ArrayList<>();
        for (Process p : processesRepo.findAll())
            result.add(p);
        return result;
    }

    public final Contractor getContractorData(long companyId) {
        return contractorRepo.findById(companyId).orElse(new Contractor());
    }

    public final List<ManagementPerson> getCompanyPeople(long companyId) {
        Contractor contractor = contractorRepo.findById(companyId).orElse(null);
        if (contractor == null) {
            return new ArrayList<>();
        }
        List<ManagementPerson> personsList = managementPersonRepo.findByContractorObjDao(contractor)
                .stream().map(
                        personDao -> new ManagementPerson(personDao)
                ).collect(Collectors.toList());
        return personsList;
    }

    public final ManagementPerson getPersonById(long id) {
        ManagementPersonDao personDao = managementPersonRepo.findById(id).orElse(null);
        if (personDao == null) {
            return null;
        }
        return new ManagementPerson(personDao);
    }

    /**
     * get invoices for city and evaluator
     *
     * @param cityShort          cityShort
     * @param evaluatorNameShort evaluatorName
     * @return List<InvoiceData>
     */
    public final List<InvoiceData> getCityRuleInvoices(String cityShort, String evaluatorNameShort) {
        City city = cityRepo.findById(cityShort).orElse(null);
        if (city == null)
            return new ArrayList<InvoiceData>();

        TypedQuery query = em.createQuery(QUERY_INVOICE_PER_RULE_CITY, InvoiceDao.class);
        query.setParameter(1, cityShort);
        query.setParameter(2, evaluatorNameShort);
        List<InvoiceData> resultList = ((List<InvoiceDao>) query.getResultList())
                .stream().map(
                        invoiceDao -> new InvoiceData(invoiceDao)
                ).collect(Collectors.toList());
        return resultList;
    }

    /**
     * Get rules for city name
     *
     * @param cityShort cityShort
     * @return List<EvaluatedResultData>
     */
    public final List<RuleCount> getCityRules(String cityShort) {
        City city = cityRepo.findById(cityShort).orElse(null);
        if (city == null)
            return new ArrayList<RuleCount>();
        TypedQuery query = em.createQuery(QUERY_RULES_PER_CITY, RuleCount.class);
        query.setParameter(1, city);

        return query.getResultList();
    }

    /**
     * get new invoices from  WS service
     *
     * @param cityShort cityShort
     */
    public final void runDetection(String cityShort) throws InterruptedException {
        Date start = new Date();
        City city = cityRepo.findById(cityShort).orElse(null);
        commSVC.getCityInvoices(city.getCity_short());
        Process process = new Process("Detection", "Dolovanie faktur pre mesto: " + city.getCity_name(), "SUCCESS", start, new Date());
        processesRepo.save(process);
    }

    /**
     * Run evaluation part to detect suspicious invoices for city
     *
     * @param cityShort city Name
     */
    public final void runEvaluation(String cityShort) {
        City city = cityRepo.findById(cityShort).orElse(null);
        Date start = new Date();


        for (Contractor contractor : contractorRepo.findAll()) {
            List<EvaluatedResult> resultList = new ArrayList<>();
            List<InvoiceDao> invoiceDaos = new ArrayList<>();

            invoiceDaos.addAll(invoicesRepo.findByCityAndContractor(city, contractor));
            resultList.addAll(evaluationService.evaluateOfFilteredInvoicesSumOverThreshold(invoiceDaos));
            resultList.addAll(evaluationService.evaluateTotalSumOverThreshold(invoiceDaos));

            resultList.forEach(evaluatedDao -> {
                EvaluatedResult evaluatedResult = evaluatedResultRepo.findByInvoiceDaoAndEvaluatorNameShort(evaluatedDao.getInvoiceDao(),evaluatedDao.getEvaluatorNameShort());
                if(evaluatedResult==null)
                    evaluatedResultRepo.save(evaluatedDao);
            });
        }

        Process process = new Process("Evaluation", "Vyhodnocovanie faktur pre mesto: " + city.getCity_name(), "SUCCESS", start, new Date());
        processesRepo.save(process);
    }

    public InvoiceData getInvoiceOverview(long id) {
        return new InvoiceData(invoicesRepo.findById(id).orElse(null));
    }
}
