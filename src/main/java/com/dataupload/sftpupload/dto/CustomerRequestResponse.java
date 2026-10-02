package com.dataupload.sftpupload.dto;

public class CustomerRequestResponse {

    private final String requestId;
    private final String status;
    private final String message;

    public CustomerRequestResponse(
            String requestId,
            String status,
            String message) {

        this.requestId = requestId;
        this.status = status;
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}