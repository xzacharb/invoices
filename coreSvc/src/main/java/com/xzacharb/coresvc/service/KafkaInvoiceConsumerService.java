package com.xzacharb.coresvc.service;

import com.xzacharb.coresvc.configuration.KafkaConsumerConfig;
import com.xzacharb.coresvc.model.dto.InvoiceData;
import com.xzacharb.coresvc.model.dto.InvoiceDataKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaInvoiceConsumerService {
    @Autowired
    KafkaConsumerConfig kafkaInvoiceConfig;

    @KafkaListener(topics = "${spring.kafka.topic.first}", groupId = "default", containerFactory = "kafkaInvoiceListenerContainerFactory")
    public void listenGroupFoo(InvoiceDataKafka invoiceData) {
        System.out.println("we have successful ordered and going to send success message: " + invoiceData);
    }
}
