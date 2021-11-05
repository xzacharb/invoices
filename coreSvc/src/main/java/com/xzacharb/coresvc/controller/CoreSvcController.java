package com.xzacharb.coresvc.controller;

import com.xzacharb.coresvc.service.CoreDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CoreSvcController {
    private static String ABOUT = "Welcome to Core Service! \n" +
            "Requests: \n" +
            "POST /overview\n" +
            "POST /overview/{city}\n" +
            "GET /invoices/{city}\n" +
            "POST /invoices/{city}/{id}\n" +
            "POST /details/contractor/{id}\n";
    @Autowired
    private CoreDbService coreDbService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String about() {
        return ABOUT;
    }

    @RequestMapping(value = "/invoices/cities/alert", method = RequestMethod.GET)
    public ResponseEntity<?> getAlertCities() throws Exception {
        return ResponseEntity.ok(coreDbService.getAlertCitiesCount());
    }
    @RequestMapping(value = "/invoices/cities", method = RequestMethod.GET)
    public ResponseEntity<?> getOverview() throws Exception {
        return ResponseEntity.ok(coreDbService.getCitiesCount());
    }

    @RequestMapping(value = "/invoices/rules/{cityShort}", method = RequestMethod.GET)
    public ResponseEntity<?> getCityRules(@PathVariable String cityShort) {
        return ResponseEntity.ok(coreDbService.getCityRules(cityShort));
    }
    @RequestMapping(value = "/invoices/rules/{cityShort}/{evaluator}", method = RequestMethod.GET)
    public ResponseEntity<?> getCityRulesInvoices(@PathVariable String cityShort,@PathVariable String evaluator) {
        return ResponseEntity.ok(coreDbService.getCityRuleCompanies(cityShort, evaluator));
    }

    @RequestMapping(value = "/invoices/{cityShort}/{evaluator}/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCityRuleCompanyInvoices(@PathVariable String cityShort, @PathVariable String evaluator, @PathVariable long companyId) {
        return ResponseEntity.ok(coreDbService.getCityRuleInvoices(cityShort, evaluator,companyId));
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
    public ResponseEntity<?> getAllProcesses() {
        return ResponseEntity.ok(coreDbService.getAllProcesses());
    }

    @RequestMapping(value = "/processes/detection/{cityShort}", method = RequestMethod.GET)
    public ResponseEntity<?> runDetectionForCity(@PathVariable String cityShort) throws Exception {
        coreDbService.runDetection(cityShort);
        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value = "/processes/evaluation/{cityShort}", method = RequestMethod.GET)
    public ResponseEntity<?> runEvaluationForCity(@PathVariable String cityShort) {
        coreDbService.runEvaluation(cityShort);
        return ResponseEntity.ok("ok");
    }

}
