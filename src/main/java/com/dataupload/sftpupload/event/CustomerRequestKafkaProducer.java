package com.dataupload.sftpupload.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestKafkaProducer {

    private static final String TOPIC =
            "customer.request.created";

    private final KafkaTemplate<String, CustomerRequestCreatedEvent>
            kafkaTemplate;

    public CustomerRequestKafkaProducer(
            KafkaTemplate<String, CustomerRequestCreatedEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(CustomerRequestCreatedEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.getRequestId(),
                event
        );
    }
}