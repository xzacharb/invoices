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

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public ResponseEntity<?> getOverview() throws Exception {
        return ResponseEntity.ok(coreDbService.getOverview());
    }

    @RequestMapping(value = "/invoices/{city}", method = RequestMethod.GET)
    public ResponseEntity<?> getCityOverview(@PathVariable String city) {
        return ResponseEntity.ok(coreDbService.getCityOverview(city));
    }
    @RequestMapping(value = "/invoices/city/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getInvoiceData(@PathVariable long id) {
        return ResponseEntity.ok(coreDbService.getInvoiceOverview(id));
    }

}
