package com.xzacharb.coresvc.model.service;

import com.xzacharb.coresvc.common.CityCount;
import com.xzacharb.coresvc.common.RuleCount;
import com.xzacharb.coresvc.model.dao.Process;
import com.xzacharb.coresvc.model.data.InvoiceData;
import com.xzacharb.coresvc.model.data.ManagementPerson;
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
    private static String QUERY_RULES_PER_CITY = "select new com.xzacharb.coresvc.common.RuleCount(r.evaluator_name,a.city.city_name,r.description,r.evaluator_name_short, count(a)) from InvoiceDao a join EvaluatedResult r on r.invoiceDao = a.id where a.city = ?1 group by r.evaluator_name, r.description, r.evaluator_name_short order by count(r) desc";
    private static String QUERY_INVOICE_PER_RULE_CITY = "select distinct a from InvoiceDao a join EvaluatedResult r on r.invoiceDao = a.id where a.city = ?1 and r.evaluator_name_short= ?2";
    private static String QUERY_ISSUES_PER_CITY_COUNT = "select new com.xzacharb.coresvc.common.CityCount(a.city, count(a)) from InvoiceDao a join EvaluatedResult r on r.invoiceDao = a.id group by a.city order by count(a) desc";

    @Autowired
    AuthorizationService authorizationService;

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
    public List<CityCount> getCitiesCount() {
        TypedQuery query = em.createQuery(QUERY_ISSUES_PER_CITY_COUNT, CityCount.class);
        return query.getResultList();
    }

    public List<Process> getAllProcesses() {
        List<Process> result = new ArrayList<Process>();
        processesRepo.findAll().forEach(result::add);
        return result;
    }

    public Contractor getContractorData(long companyId) {
        return contractorRepo.findById(companyId).orElse(new Contractor());
    }

    public List<ManagementPerson> getCompanyPeople(long companyId) {
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

    public ManagementPerson getPersonById(long id) {
        ManagementPersonDao personDao = managementPersonRepo.findById(id).orElse(null);
        if (personDao == null) {
            return null;
        }
        return new ManagementPerson(personDao);
    }

    /**
     * get invoices for city and evaluator
     *
     * @param cityName           cityName
     * @param evaluatorNameShort evaluatorName
     * @return List<InvoiceData>
     */
    public List<InvoiceData> getCityRuleInvoices(String cityName, String evaluatorNameShort) {
        City city = cityRepo.findById(cityName).orElse(null);
        if (city == null)
            return new ArrayList<InvoiceData>();

        TypedQuery query = em.createQuery(QUERY_INVOICE_PER_RULE_CITY, InvoiceDao.class);
        query.setParameter(1, city);
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
     * @param cityName cityName
     * @return List<EvaluatedResultData>
     */
    public List<RuleCount> getCityRules(String cityName) {
        City city = cityRepo.findById(cityName).orElse(null);
        if (city == null)
            return new ArrayList<RuleCount>();
        TypedQuery query = em.createQuery(QUERY_RULES_PER_CITY, RuleCount.class);
        query.setParameter(1, city);

        return query.getResultList();
    }

    /**
     * get new invoices from  WS service
     *
     * @param cityName city Name
     */
    public List<Process> runDetection(String cityName) {
        City city = cityRepo.findById(cityName).orElse(null);
        return getAllProcesses();
    }

    /**
     * Run evaluation part to detect suspicious invoices for city
     *
     * @param cityName city Name
     */
    public List<Process> runEvaluation(String cityName) {
        City city = cityRepo.findById(cityName).orElse(null);
        //if (city == null)
        return getAllProcesses();
    }

    public InvoiceData getInvoiceOverview(long id) {
        return new InvoiceData(invoicesRepo.findById(id).orElse(null));
    }
}
