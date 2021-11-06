package com.xzacharb.coresvc.impl.controller;

import com.xzacharb.coresvc.impl.service.DefaultCoreService;
import com.xzacharb.coresvc.infra.controller.CoreController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CoreSvcController implements CoreController {
    private static final String ABOUT = "Welcome to Core Service! \n";

    @Autowired
    private DefaultCoreService coreDbService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String about() {
        return ABOUT;
    }

    @RequestMapping(value = "/invoices/cities/alert", method = RequestMethod.GET)
    public ResponseEntity<?> getAlertCities() {
        return ResponseEntity.ok(coreDbService.getAlertCities());
    }

    @RequestMapping(value = "/invoices/cities", method = RequestMethod.GET)
    public ResponseEntity<?> getOverview() {
        return ResponseEntity.ok(coreDbService.getOverview());
    }

    @RequestMapping(value = "/invoices/rules/{cityShort}", method = RequestMethod.GET)
    public ResponseEntity<?> getCityRules(@PathVariable String cityShort) {
        return ResponseEntity.ok(coreDbService.getCityRules(cityShort));
    }

    @RequestMapping(value = "/invoices/rules/{cityShort}/{evaluator}", method = RequestMethod.GET)
    public ResponseEntity<?> getCityRulesInvoices(@PathVariable String cityShort, @PathVariable String evaluator) {
        return ResponseEntity.ok(coreDbService.getCityRulesInvoices(cityShort, evaluator));
    }

    @RequestMapping(value = "/invoices/{cityShort}/{evaluator}/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCityRuleCompanyInvoices(@PathVariable String cityShort, @PathVariable String evaluator, @PathVariable long companyId) {
        return ResponseEntity.ok(coreDbService.getCityRuleCompanyInvoices(cityShort, evaluator, companyId));
    }

    @RequestMapping(value = "/invoices/company/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getContractorData(@PathVariable long companyId) {
        return ResponseEntity.ok(coreDbService.getContractorData(companyId));
    }

    @RequestMapping(value = "/invoices/company/people/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCompanyPeople(@PathVariable long companyId) {
        return ResponseEntity.ok(coreDbService.getCompanyPeople(companyId));
    }

    @RequestMapping(value = "/invoices/people/{personId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPersonById(@PathVariable long personId) {
        return ResponseEntity.ok(coreDbService.getPersonById(personId));
    }

    @RequestMapping(value = "/processes/history", method = RequestMethod.GET)
    public ResponseEntity<?> getProcessHistory() {
        return ResponseEntity.ok(coreDbService.getProcessHistory());
    }

    @RequestMapping(value = "/processes/detection/{cityShort}", method = RequestMethod.GET)
    public void runDetectionForCity(@PathVariable String cityShort) {
        coreDbService.runDetectionForCity(cityShort);
    }

    @RequestMapping(value = "/processes/evaluation/{cityShort}", method = RequestMethod.GET)
    public void runEvaluationForCity(@PathVariable String cityShort) {
        coreDbService.runEvaluationForCity(cityShort);
    }

}
