package com.xzacharb.coresvc.infra.controller;

import org.springframework.http.ResponseEntity;

public interface CoreController {
    public ResponseEntity<?> getAlertCities();
    public ResponseEntity<?> getOverview();
    public ResponseEntity<?> getCityRules(String cityShort);
    public ResponseEntity<?> getCityRulesInvoices(String cityShort,String evaluator);
    public ResponseEntity<?> getCityRuleCompanyInvoices(String cityShort, String evaluator, long companyId);
    public ResponseEntity<?> getContractorData(long companyId);
    public ResponseEntity<?> getCompanyPeople(long companyId);
    public ResponseEntity<?> getPersonById(long personId);
    public ResponseEntity<?> getProcessHistory();
    public void runDetectionForCity(String cityShort) throws Exception;
    public void runEvaluationForCity(String cityShort);
}
