package com.xzacharb.coresvc.model.service;

import com.xzacharb.coresvc.configuration.KafkaConsumerConfig;
import com.xzacharb.coresvc.model.data.InvoiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaInvoiceConsumerService {
    @Autowired
    KafkaConsumerConfig kafkaInvoiceConfig;

    @KafkaListener(topics = "${spring.kafka.topic.first}", groupId = "default", containerFactory = "kafkaInvoiceListenerContainerFactory")
    public void listenGroupFoo(InvoiceData invoiceData) {
        System.out.println("we have successful ordered and going to send success message: " + invoiceData);
    }
}
