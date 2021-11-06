package com.xzacharb.coresvc.impl.controller;

import com.xzacharb.coresvc.impl.model.dto.InvoiceDataKafka;
import com.xzacharb.coresvc.impl.service.microservices.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/kafka")
public class KafkaProducerController {
    @Autowired
    KafkaProducerService kafkaProducerService;

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publish(@RequestBody InvoiceDataKafka invoiceData) throws Exception {
        return kafkaProducerService.sendMessage(invoiceData);
    }
}
