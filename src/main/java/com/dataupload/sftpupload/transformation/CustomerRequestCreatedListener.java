package com.dataupload.sftpupload.transformation;

import com.dataupload.sftpupload.event.CustomerRequestCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestCreatedListener {

    private final OrderTransformationService transformationService;

    public CustomerRequestCreatedListener(
            OrderTransformationService transformationService) {
        this.transformationService = transformationService;
    }

    @KafkaListener(
            topics = "customer.request.created",
            groupId = "transformation-service"
    )
    public void handleCustomerRequest(
            CustomerRequestCreatedEvent event) {

        System.out.println(
                "Received customer request: "
                        + event.getRequestId()
        );

        if ("ORDER_EXPORT".equals(event.getRequestType())) {

            String csv =
                    transformationService.generateOrderExport(
                            event.getRequestId(),
                            event.getCustomerId(),
                            event.getRequestedDate()
                    );

            System.out.println("Generated CSV:");
            System.out.println(csv);
        }
    }
}