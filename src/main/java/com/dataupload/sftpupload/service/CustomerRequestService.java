package com.dataupload.sftpupload.service;

import com.dataupload.sftpupload.dto.CustomerRequest;
import com.dataupload.sftpupload.dto.CustomerRequestResponse;
import com.dataupload.sftpupload.entity.CustomerRequestEntity;
import com.dataupload.sftpupload.event.CustomerRequestCreatedEvent;
import com.dataupload.sftpupload.event.CustomerRequestKafkaProducer;
import com.dataupload.sftpupload.repository.CustomerRequestRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerRequestService {

    private final CustomerRequestRepository repository;
    private final CustomerRequestKafkaProducer kafkaProducer;


    public CustomerRequestService(
            CustomerRequestRepository repository,
            CustomerRequestKafkaProducer kafkaProducer) {

        this.repository = repository;
        this.kafkaProducer = kafkaProducer;
    }


    public CustomerRequestResponse createRequest(
            CustomerRequest request) {

        String requestId = UUID.randomUUID().toString();

        CustomerRequestEntity entity =
                new CustomerRequestEntity(
                        requestId,
                        request.getCustomerId(),
                        request.getRequestType(),
                        request.getRequestedDate(),
                        request.getDestination(),
                        "RECEIVED"
                );

        repository.save(entity);

        CustomerRequestCreatedEvent event =
                new CustomerRequestCreatedEvent(
                        requestId,
                        request.getCustomerId(),
                        request.getRequestType(),
                        request.getRequestedDate(),
                        request.getDestination()
                );

        kafkaProducer.publish(event);

        return new CustomerRequestResponse(
                requestId,
                "RECEIVED",
                "Customer request received successfully"
        );
    }
}