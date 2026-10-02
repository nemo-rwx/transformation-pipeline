package com.dataupload.sftpupload.event;

import java.time.LocalDate;

public class CustomerCsvGeneratedEvent {

    private String requestId;
    private String customerId;
    private String requestType;
    private LocalDate requestedDate;
    private String fileName;
    private String filePath;

    public CustomerCsvGeneratedEvent() {
    }

    public CustomerCsvGeneratedEvent(
            String requestId,
            String customerId,
            String requestType,
            LocalDate requestedDate,
            String fileName,
            String filePath) {

        this.requestId = requestId;
        this.customerId = customerId;
        this.requestType = requestType;
        this.requestedDate = requestedDate;
        this.fileName = fileName;
        this.filePath = filePath;
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

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }
}