package com.xzacharb.coresvc.impl.service.invoices;

import com.xzacharb.coresvc.impl.component.RepositoryFacade;
import com.xzacharb.coresvc.impl.model.dao.*;
import com.xzacharb.coresvc.impl.model.dao.Process;
import com.xzacharb.coresvc.impl.model.dto.InfoData;
import com.xzacharb.coresvc.impl.model.dto.InvoiceData;
import com.xzacharb.coresvc.impl.model.dto.ManagementPerson;
import com.xzacharb.coresvc.impl.model.dto.RuleCount;
import com.xzacharb.coresvc.infra.service.invoices.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultInvoiceService implements InvoiceService {
    private static String QUERY_RULES_PER_CITY = "select new com.xzacharb.coresvc.impl.model.dto.RuleCount(r.evaluator_name,a.city.city_name,a.city.city_short,r.description,r.evaluatorNameShort, count(a)) from InvoiceDao a join EvaluatedResult r on r.invoiceDao = a.id where a.city = ?1 group by r.evaluator_name, r.description, r.evaluatorNameShort order by count(r) desc";
    private static String QUERY_COMPANIES_PER_CITY_RULE = "select new com.xzacharb.coresvc.impl.model.dto.InfoData(c.name, c.id, count(c)) from EvaluatedResult er join InvoiceDao a on er.invoiceDao = a.id join Contractor c on a.contractor = c.id where a.city = ?1 and er.evaluatorNameShort = ?2 group by c.id order by count(c) desc";
    private static String QUERY_INVOICE_PER_RULE_CITY = "select distinct a from InvoiceDao a join EvaluatedResult r on r.invoiceDao = a.id where a.city.city_short = ?1 and r.evaluatorNameShort= ?2 and a.contractor.id = ?3";
    private static String QUERY_ISSUES_PER_CITY_COUNT = "select new com.xzacharb.coresvc.impl.model.dto.InfoData(a.city.city_name,a.city.city_short, count(a)) from InvoiceDao a join EvaluatedResult r on r.invoiceDao = a.id  group by a.city order by count(a) desc";

    @Autowired
    private RepositoryFacade repository;

    @PersistenceContext
    private EntityManager em;

    /**
     * Get count of issued invoices per city
     *
     * @return List<InfoData>
     */
    public final List<InfoData> getCitiesCount() {
        List<InfoData> cityCounts = new ArrayList<>();
        for (City city : repository.cityRepo.findAll()) {
            cityCounts.add(new InfoData(city.getCity_name(), city.getCity_short(), 0l));
        }
        return cityCounts;
    }

    public final List<InfoData> getAlertCitiesCount() {
        TypedQuery query = em.createQuery(QUERY_ISSUES_PER_CITY_COUNT, InfoData.class);
        return query.getResultList();
    }

    public final List<Process> getAllProcesses() {
        List<Process> result = new ArrayList<>();
        for (Process p : repository.processesRepo.findAll())
            result.add(p);
        return result;
    }

    public final Contractor getContractorData(long companyId) {
        return repository.contractorRepo.findById(companyId).orElse(new Contractor());
    }

    public final List<ManagementPerson> getCompanyPeople(long companyId) {
        Contractor contractor = repository.contractorRepo.findById(companyId).orElse(null);
        if (contractor == null) {
            return new ArrayList<>();
        }
        List<ManagementPerson> personsList = repository.managementPersonRepo.findByContractorObjDao(contractor)
                .stream().map(
                        personDao -> new ManagementPerson(personDao)
                ).collect(Collectors.toList());
        return personsList;
    }

    public final ManagementPerson getPersonById(long id) {
        ManagementPersonDao personDao = repository.managementPersonRepo.findById(id).orElse(null);
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
    public final List<InvoiceData> getCityRuleInvoices(String cityShort, String evaluatorNameShort, long companyId) {
        City city = repository.cityRepo.findById(cityShort).orElse(null);
        if (city == null)
            return new ArrayList<InvoiceData>();

        TypedQuery query = em.createQuery(QUERY_INVOICE_PER_RULE_CITY, InvoiceDao.class);
        query.setParameter(1, cityShort);
        query.setParameter(2, evaluatorNameShort);
        query.setParameter(3, companyId);
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
        City city = repository.cityRepo.findById(cityShort).orElse(null);
        if (city == null)
            return new ArrayList<RuleCount>();
        TypedQuery query = em.createQuery(QUERY_RULES_PER_CITY, RuleCount.class);
        query.setParameter(1, city);

        return query.getResultList();
    }

    /**
     * Get list of companies data for city and rule name
     *
     * @param cityShort cityShort
     * @return List<EvaluatedResultData>
     */
    public final List<InfoData> getCityRuleCompanies(String cityShort, String ruleNameShort) {
        City city = repository.cityRepo.findById(cityShort).orElse(null);
        if (city == null)
            return new ArrayList<InfoData>();
        TypedQuery query = em.createQuery(QUERY_COMPANIES_PER_CITY_RULE, InfoData.class);
        query.setParameter(1, city);
        query.setParameter(2, ruleNameShort);

        return query.getResultList();
    }


    public InvoiceData getInvoiceOverview(long id) {
        return new InvoiceData(repository.invoicesRepo.findById(id).orElse(null));
    }
}
