package com.xzacharb.coresvc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CoreSvcController {
    private static String about = "Welcome to Core Service! \n" +
            "Requests: \n" +
            "POST /overview\n" +
            "POST /overview/{city}\n" +
            "POST /invoices/{city}\n" +
            "POST /invoices/{city}/{id}\n" +
            "POST /details/contractor/{id}\n";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String about() {
        return about;
    }
}
