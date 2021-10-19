package com.xzacharb.coresvc.model.service;

import com.xzacharb.coresvc.model.data.InvoiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, InvoiceData> kafkaTemplate;

    public String sendMessage(InvoiceData invoiceData) {
        kafkaTemplate.send("invoices_topic", invoiceData);
        System.out.println("sent Message to kafka topic : " + invoiceData);
        return "sent Message to kafka topic : " + invoiceData;
    }
}
