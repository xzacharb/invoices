package com.xzacharb.coresvc.impl.common;

import com.xzacharb.coresvc.impl.model.dto.EvaluatedResultData;
import com.xzacharb.coresvc.impl.model.dto.InvoiceDataKafka;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Bean
    public ConsumerFactory<String, InvoiceDataKafka> invoiceConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, InvoiceDataKafka.class.getPackage().getName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, InvoiceDataKafka.class);
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, InvoiceDataKafka>
    kafkaInvoiceListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, InvoiceDataKafka> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(invoiceConsumerFactory());
        return factory;
    }
    @Bean
    public ConsumerFactory<String, EvaluatedResultData> evaluationConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, EvaluatedResultData.class.getPackage().getName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, EvaluatedResultData.class);
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EvaluatedResultData>
    kafkaEvaluationListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, EvaluatedResultData> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(evaluationConsumerFactory());
        return factory;
    }
    /*@KafkaListener(topics = "${spring.kafka.topic.first}", groupId = "default", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(InvoiceData invoiceData) {
        System.out.println("we have successful ordered and going to send success message: " + invoiceData);
    }*/
    /*@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @KafkaListener(topics = "${spring.kafka.topic.first}", groupId = "default", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(InvoiceData invoiceData) {
        System.out.println("we have successful ordered and going to send success message: " + invoiceData);
    }
    @Bean
    public ConsumerFactory<String, InvoiceData> consumerFactory() {
        JsonDeserializer<HeaderEnricher.Container> deserializer = new JsonDeserializer<>(HeaderEnricher.Container.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, InvoiceData.class.getPackage().getName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, InvoiceData.class);
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, InvoiceData>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, InvoiceData> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }*/
}
