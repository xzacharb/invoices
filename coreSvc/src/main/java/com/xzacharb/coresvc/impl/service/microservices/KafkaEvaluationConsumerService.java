package com.xzacharb.coresvc.impl.service.microservices;

import com.xzacharb.coresvc.impl.common.KafkaConsumerConfig;
import com.xzacharb.coresvc.impl.model.dto.EvaluatedResultData;
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
