package com.xzacharb.coresvc.model.service;

import com.xzacharb.coresvc.configuration.KafkaConsumerConfig;
import com.xzacharb.coresvc.model.dao.EvaluatedResult;
import com.xzacharb.coresvc.model.data.EvaluatedResultData;
import com.xzacharb.coresvc.model.data.InvoiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaEvaluationConsumerService {
    @Autowired
    KafkaConsumerConfig kafkaInvoiceConfig;

    @KafkaListener(topics = "${spring.kafka.topic.second}", groupId = "default", containerFactory = "kafkaEvaluationListenerContainerFactory")
    public void listenGroupFoo(EvaluatedResultData evaluatedResult) {
        System.out.println("we have successful ordered and going to send success message: " + evaluatedResult);
    }
}
