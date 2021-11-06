package com.xzacharb.coresvc.impl.service;

import com.xzacharb.coresvc.infra.service.CoreService;
import com.xzacharb.coresvc.infra.service.invoices.InvoiceService;
import com.xzacharb.coresvc.infra.service.process.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DefaultCoreService implements CoreService {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProcessService processesService;

    @Override
    public ResponseEntity<?> getAlertCities() {
        return ResponseEntity.ok(invoiceService.getAlertCitiesCount());
    }

    @Override
    public ResponseEntity<?> getOverview() {
        return ResponseEntity.ok(invoiceService.getCitiesCount());
    }

    @Override
    public ResponseEntity<?> getCityRules(String cityShort) {
        return ResponseEntity.ok(invoiceService.getCityRules(cityShort));
    }

    @Override
    public ResponseEntity<?> getCityRulesInvoices(String cityShort, String evaluator) {
        return ResponseEntity.ok(invoiceService.getCityRuleCompanies(cityShort, evaluator));
    }

    @Override
    public ResponseEntity<?> getCityRuleCompanyInvoices(String cityShort, String evaluator, long companyId) {
        return ResponseEntity.ok(invoiceService.getCityRuleInvoices(cityShort, evaluator,companyId));
    }

    @Override
    public ResponseEntity<?> getContractorData(long companyId) {
        return ResponseEntity.ok(invoiceService.getContractorData(companyId));
    }

    @Override
    public ResponseEntity<?> getCompanyPeople(long companyId) {
        return ResponseEntity.ok(invoiceService.getCompanyPeople(companyId));
    }

    @Override
    public ResponseEntity<?> getPersonById(long personId) {
        return ResponseEntity.ok(invoiceService.getPersonById(personId));
    }

    @Override
    public ResponseEntity<?> getProcessHistory() {
        return ResponseEntity.ok(invoiceService.getAllProcesses());
    }



    @Override
    public void runDetectionForCity(String cityShort) {
        processesService.runDetection(cityShort);
    }

    @Override
    public void runEvaluationForCity(String cityShort) {
        processesService.runEvaluation(cityShort);
    }
}
