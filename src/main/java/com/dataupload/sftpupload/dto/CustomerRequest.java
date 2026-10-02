package com.dataupload.sftpupload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CustomerRequest {

    @NotBlank
    @Size(max = 50)
    private String customerId;

    @NotBlank
    @Pattern(
            regexp = "ORDER_EXPORT|INVOICE_EXPORT",
            message = "requestType must be ORDER_EXPORT or INVOICE_EXPORT"
    )
    private String requestType;

    @NotNull(message = "requestedDate is required")
    private LocalDate requestedDate;

    @NotBlank
    @Pattern(
            regexp = "CUSTOMER_SFTP",
            message = "destination must be CUSTOMER_SFTP"
    )
    private String destination;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}