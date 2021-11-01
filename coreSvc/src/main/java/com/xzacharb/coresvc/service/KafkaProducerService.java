package com.xzacharb.coresvc.service;

import com.xzacharb.coresvc.model.dto.InvoiceData;
import com.xzacharb.coresvc.model.dto.InvoiceDataKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, InvoiceDataKafka> kafkaTemplate;
    @Value("${spring.kafka.topic.first}")
    private String topic;

    public String sendMessage(InvoiceDataKafka invoiceData) {
        kafkaTemplate.send(topic, invoiceData);
        System.out.println("sent Message to kafka topic : " + invoiceData);
        return "sent Message to kafka topic : " + invoiceData;
    }
}
