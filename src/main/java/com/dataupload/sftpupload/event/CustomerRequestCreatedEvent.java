package com.dataupload.sftpupload.event;

import java.time.LocalDate;

public class CustomerRequestCreatedEvent {

    private String requestId;
    private String customerId;
    private String requestType;
    private LocalDate requestedDate;
    private String destination;

    public CustomerRequestCreatedEvent() {
    }

    public CustomerRequestCreatedEvent(
            String requestId,
            String customerId,
            String requestType,
            LocalDate requestedDate,
            String destination) {

        this.requestId = requestId;
        this.customerId = customerId;
        this.requestType = requestType;
        this.requestedDate = requestedDate;
        this.destination = destination;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getRequestType() {
        return requestType;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public String getDestination() {
        return destination;
    }


}