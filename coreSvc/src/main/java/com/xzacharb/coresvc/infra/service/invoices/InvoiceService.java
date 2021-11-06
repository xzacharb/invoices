package com.xzacharb.coresvc.infra.service.invoices;

import com.xzacharb.coresvc.impl.model.dao.*;
import com.xzacharb.coresvc.impl.model.dao.Process;
import com.xzacharb.coresvc.impl.model.dto.InfoData;
import com.xzacharb.coresvc.impl.model.dto.InvoiceData;
import com.xzacharb.coresvc.impl.model.dto.ManagementPerson;
import com.xzacharb.coresvc.impl.model.dto.RuleCount;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface InvoiceService {
    /**
     * Get count of issued invoices per city
     *
     * @return
     */
    public List<InfoData> getCitiesCount();

    /**
     * Get count of issued invoices per city
     *
     * @return
     */
    public List<InfoData> getAlertCitiesCount();

    public List<Process> getAllProcesses();

    public Contractor getContractorData(long companyId);

    public List<ManagementPerson> getCompanyPeople(long companyId);

    public ManagementPerson getPersonById(long id);

    /**
     * get invoices for city and evaluator
     *
     * @param cityShort          cityShort
     * @param evaluatorNameShort evaluatorName
     * @return List<InvoiceData>
     */
    public List<InvoiceData> getCityRuleInvoices(String cityShort, String evaluatorNameShort, long companyId);

    /**
     * Get rules for city name
     *
     * @param cityShort cityShort
     * @return List<EvaluatedResultData>
     */
    public List<RuleCount> getCityRules(String cityShort);

    /**
     * Get list of companies data for city and rule name
     *
     * @param cityShort cityShort
     * @return List<EvaluatedResultData>
     */
    public List<InfoData> getCityRuleCompanies(String cityShort, String ruleNameShort);

    public InvoiceData getInvoiceOverview(long id);
}
