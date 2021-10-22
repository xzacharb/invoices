package com.xzacharb.coresvc.model.service;

import com.xzacharb.coresvc.model.data.InvoiceData;
import com.xzacharb.coresvc.model.data.ManagementPerson;
import com.xzacharb.coresvc.model.data.TupleData;
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
    private static String QUERY_ISSUES_PER_CITY = "select distinct a from InvoiceDao a join EvaluatedResultData r on r.invoiceDao = a.id where a.city = ?1";
    private static String QUERY_ISSUES_PER_CITY_COUNT = "select a.city, count(a) from InvoiceDao a join EvaluatedResultData r on r.invoiceDao = a.id group by a.city";

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
    public ManagementPersonRepo managementPersonRepo;

    @Autowired
    public ManagementTypeRepo managementTypeRepo;

    @Autowired
    public InvoicesRepo invoicesRepo;

    @Autowired
    public EvaluatedResultRepo evaluatedResultRepo;

    @PersistenceContext
    private EntityManager em;

    public Map<String, String> getOverview() {
        TypedQuery query = em.createQuery(QUERY_ISSUES_PER_CITY_COUNT, Object[].class);
        List<Object[]> resultList = query.getResultList();
        List<TupleData<City, Long>> listTupleData =
                resultList.stream().map(tupleDataArray ->
                        new TupleData<>((City) tupleDataArray[0], (Long) tupleDataArray[1])
                ).collect(Collectors.toList());
        Map cities = new HashMap<>();
        listTupleData.forEach(tupleData ->
                cities.put(tupleData.getFirstObject().getCity_name(), tupleData.getSecondNumericObject())
        );
        Map overview = new HashMap<>();
        overview.put("cities", cities);
        overview.put("totalInvoices", invoicesRepo.count());
        overview.put("totalSuspiciousIssues", evaluatedResultRepo.count());
        return overview;
    }

    public List<InvoiceData> getCityOverview(String cityName) {
        City city = cityRepo.findById(cityName).orElse(null);
        if (city == null)
            return new ArrayList<InvoiceData>();
        TypedQuery query = em.createQuery(QUERY_ISSUES_PER_CITY, InvoiceDao.class);
        query.setParameter(1, city);
        List<InvoiceDao> resultList = query.getResultList();

        return resultList.stream().map(invoiceDao ->
                new InvoiceData(invoiceDao)).collect(Collectors.toList());
    }

    /**
     * Get list of management people for current invoice id
     * @param id invoice id
     * @return contractor management
     */
    public List<ManagementPerson> getManagementPeople(long id) {
        InvoiceDao invoiceDao = invoicesRepo.findById(id).orElse(null);
        if (invoiceDao == null)
            return new ArrayList<>();
        List<ManagementPerson> managementPeople =
                managementPersonRepo.findByContractorObjDao(invoiceDao.getContractor())
                        .stream().map(
                                peopleDao -> new ManagementPerson(peopleDao)
                        ).collect(Collectors.toList());

        return managementPeople;
    }

    /**
     * get new invoices from  WS service
     * @param cityName city Name
     */
    public void runDetection(String cityName){
        City city = cityRepo.findById(cityName).orElse(null);
        if (city == null)
            return ;


    }

    /**
     * Run evaluation part to detect suspicious invoices for city
     * @param cityName city Name
     */
    public void runEvaluation(String cityName){
        City city = cityRepo.findById(cityName).orElse(null);
        if (city == null)
            return ;
    }

    public InvoiceData getInvoiceOverview(long id) {
        return new InvoiceData(invoicesRepo.findById(id).orElse(null));
    }
}
