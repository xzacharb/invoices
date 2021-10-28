package com.xzacharb.coresvc.controller;

import com.xzacharb.coresvc.model.service.CoreDbService;
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

    @RequestMapping(value = "/invoices/cities", method = RequestMethod.GET)
    public ResponseEntity<?> getOverview() throws Exception {
        return ResponseEntity.ok(coreDbService.getCitiesCount());
    }

    @RequestMapping(value = "/invoices/rules/{city}", method = RequestMethod.GET)
    public ResponseEntity<?> getCityRules(@PathVariable String city) {
        return ResponseEntity.ok(coreDbService.getCityRules(city));
    }

    @RequestMapping(value = "/invoices/{city}/{evaluator}", method = RequestMethod.GET)
    public ResponseEntity<?> getCityRuleInvoices(@PathVariable String city, @PathVariable String evaluator) {
        return ResponseEntity.ok(coreDbService.getCityRuleInvoices(city, evaluator));
    }

    @RequestMapping(value = "/invoices/company/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getContractorData(@PathVariable long companyId) {
        return ResponseEntity.ok(coreDbService.getContractorData(companyId));
    }

    @RequestMapping(value = "/invoices/company/people/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCompanyPeople(@PathVariable long companyId) {
        return ResponseEntity.ok(coreDbService.getCompanyPeople(companyId));
    }
    @RequestMapping(value = "/invoices/people/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCompanyPeople(@PathVariable long companyId) {
        return ResponseEntity.ok(coreDbService.getPersonById(companyId));
    }

    @RequestMapping(value = "/detection/{city}", method = RequestMethod.GET)
    public ResponseEntity<?> runDetectionForCity(@PathVariable String city) {
        coreDbService.runDetection(city);
        return ResponseEntity.ok("run detection");
    }

    @RequestMapping(value = "/evaluation/{city}", method = RequestMethod.GET)
    public ResponseEntity<?> runEvaluationForCity(@PathVariable String city) {
        coreDbService.runEvaluation(city);
        return ResponseEntity.ok("run evaluation");
    }

}
