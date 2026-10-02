package com.dataupload.sftpupload.controller;

import com.dataupload.sftpupload.dto.CustomerRequest;
import com.dataupload.sftpupload.dto.CustomerRequestResponse;
import com.dataupload.sftpupload.service.CustomerRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer-requests")
public class CustomerController {

    private final CustomerRequestService service;

    public CustomerController(CustomerRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerRequestResponse> createRequest(
            @Valid @RequestBody CustomerRequest request) {

        return ResponseEntity.ok(
                service.createRequest(request)
        );
    }
}