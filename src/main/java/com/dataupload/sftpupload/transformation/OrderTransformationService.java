package com.dataupload.sftpupload.transformation;

import com.dataupload.sftpupload.entity.OrderEntity;
import com.dataupload.sftpupload.event.CustomerCsvGeneratedEvent;
import com.dataupload.sftpupload.event.CustomerCsvGeneratedKafkaProducer;
import com.dataupload.sftpupload.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderTransformationService {

    private final OrderRepository orderRepository;
    private final OrderCsvGenerator csvGenerator;
    private final CustomerCsvGeneratedKafkaProducer kafkaProducer;

    public OrderTransformationService(
            OrderRepository orderRepository,
            OrderCsvGenerator csvGenerator,
            CustomerCsvGeneratedKafkaProducer kafkaProducer) {

        this.orderRepository = orderRepository;
        this.csvGenerator = csvGenerator;
        this.kafkaProducer = kafkaProducer;
    }

    public String generateOrderExport(
            String requestId,
            String customerId,
            LocalDate requestedDate) {

        List<OrderEntity> orders =
                orderRepository.findByCustomerIdAndOrderDate(
                        customerId,
                        requestedDate
                );

        String csv = csvGenerator.generate(orders);

        try {
            Path outputDirectory =
                    Paths.get("generated-files");

            Files.createDirectories(outputDirectory);

            String fileName =
                    "ORDER_EXPORT_"
                            + customerId
                            + "_"
                            + requestedDate
                            + ".csv";

            Path filePath =
                    outputDirectory.resolve(fileName);

            Files.writeString(filePath, csv);

            System.out.println(
                    "CSV file generated: "
                            + filePath.toAbsolutePath()
            );

            CustomerCsvGeneratedEvent event =
                    new CustomerCsvGeneratedEvent(
                            requestId,
                            customerId,
                            "ORDER_EXPORT",
                            requestedDate,
                            fileName,
                            filePath.toString()
                    );

            kafkaProducer.publish(event);

            return csv;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to write CSV file",
                    e
            );
        }
    }
}