package com.dataupload.sftpupload.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerCsvGeneratedKafkaProducer {

    private static final String TOPIC = "customer.csv.generated";

    private final KafkaTemplate<String, CustomerCsvGeneratedEvent> kafkaTemplate;

    public CustomerCsvGeneratedKafkaProducer(
            KafkaTemplate<String, CustomerCsvGeneratedEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(CustomerCsvGeneratedEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.getRequestId(),
                event
        );
    }
}