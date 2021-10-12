package com.xzacharb.coresvc.model.service;

import com.xzacharb.coresvc.model.dao.*;
import com.xzacharb.coresvc.model.objects.*;
import com.xzacharb.coresvc.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoreDbService {
    private static String QUERY_ISSUES_PER_CITY = "select a from InvoiceDao a join EvaluatedResultDao r on r.invoiceDao = a.id where a.cityDao = ?1";
    //private static String QUERY_ISSUES_PER_CITY = "select a from InvoiceDao a";
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

    public Map<String, String> getOverview(AuthToken token) {
        Map overview = new HashMap<>();
        overview.put("token", token.getToken());
        overview.put("totalInvoices", invoicesRepo.count());
        overview.put("totalSuspiciousIssues", evaluatedResultRepo.count());
        overview.put("countOfContractors", contractorRepo.count());
        overview.put("countOfCities", cityRepo.count());
        overview.put("countOfLegalForms", legalFormRepo.count());
        overview.put("countOfManagementPeople", managementPersonRepo.count());
        return overview;
    }

    public List<InvoiceObj> getCityOverview(String city) {
        CityDao cityDao = cityRepo.findById(city).orElse(null);
        if (cityDao == null)
            return new ArrayList<InvoiceObj>();
        TypedQuery query = em.createQuery(QUERY_ISSUES_PER_CITY, InvoiceDao.class);
        query.setParameter(1, cityDao);
        List<InvoiceDao> resultList = query.getResultList();

        return resultList.stream().map(invoiceDao ->
                new InvoiceObj(
                        invoiceDao.getId(), invoiceDao.getPrice(), invoiceDao.getSubject(), invoiceDao.getDescription(), invoiceDao.getComment(), invoiceDao.getDate_signed(), invoiceDao.getDate_published(), invoiceDao.getSource(), invoiceDao.getCityDao(), invoiceDao.getContractorDao()
                )).collect(Collectors.toList());
    }


    public List<ManagementPersonDao> getInvoiceOverview(long id) {
        InvoiceDao invoiceDao = invoicesRepo.findById(id).orElse(null);
        if (invoiceDao == null)
            return new ArrayList<ManagementPersonDao>();
        List<ManagementPersonDao> managementPersonDaos = managementPersonRepo.findByContractorObjDao(invoiceDao.getContractorDao());

        return managementPersonDaos;
        /*return managementPersonDaos.stream().map(managementPersonDao ->
                new ManagementPersonObj(
                        managementPersonDao.getId(), managementPersonDao.getName(), managementPersonDao.getMiddle_name(), managementPersonDao.getSure_name(), managementPersonDao.getAddress(), managementPersonDao.getSource(), managementPersonDao.getDate_start(), new RoleObj(managementPersonDao.getRoleDao().getRole_name()), managementPersonDao.getContractorObjDao(), managementPersonDao.getCityObjDao(), managementPersonDao.getManagementTypeDao()
                )).collect(Collectors.toList());*/
    }
}
