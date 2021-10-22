package com.xzacharb.coresvc.model.service;

import com.xzacharb.coresvc.model.data.InvoiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, InvoiceData> kafkaTemplate;
    @Value("${spring.kafka.topic.first}")
    private String topic;
    public String sendMessage(InvoiceData invoiceData) {
        kafkaTemplate.send(topic, invoiceData);
        System.out.println("sent Message to kafka topic : " + invoiceData);
        return "sent Message to kafka topic : " + invoiceData;
    }
}
