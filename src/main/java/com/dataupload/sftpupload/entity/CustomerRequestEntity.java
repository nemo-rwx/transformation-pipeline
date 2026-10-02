package com.dataupload.sftpupload.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer_requests")
public class CustomerRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String requestId;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String requestType;

    @Column(nullable = false)
    private LocalDate requestedDate;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String status;

    public CustomerRequestEntity() {
    }

    public CustomerRequestEntity(
            String requestId,
            String customerId,
            String requestType,
            LocalDate requestedDate,
            String destination,
            String status) {

        this.requestId = requestId;
        this.customerId = customerId;
        this.requestType = requestType;
        this.requestedDate = requestedDate;
        this.destination = destination;
        this.status = status;
    }

    public Long getId() {
        return id;
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

    public String getStatus() {
        return status;
    }
}