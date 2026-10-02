package com.dataupload.sftpupload.controller;

import com.dataupload.sftpupload.transformation.OrderTransformationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/test-transformation")
public class TransformationTestController {

    private final OrderTransformationService transformationService;

    public TransformationTestController(
            OrderTransformationService transformationService) {
        this.transformationService = transformationService;
    }

    @GetMapping("/order-export")
    public String generateOrderExport(
            @RequestParam String customerId,
            @RequestParam LocalDate requestedDate) {

        return transformationService.generateOrderExport(
                "TEST-REQUEST-ID",
                customerId,
                requestedDate
        );
    }
}