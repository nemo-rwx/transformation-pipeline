package com.dataupload.sftpupload.config;

import com.dataupload.sftpupload.entity.OrderEntity;
import com.dataupload.sftpupload.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class OrderDataGenerator {

    private static final int NUMBER_OF_ORDERS = 10_000;
    private static final int BATCH_SIZE = 500;

    private static final String[] PRODUCTS = {
            "Laptop",
            "Keyboard",
            "Mouse",
            "Monitor",
            "Headphones",
            "Printer",
            "Webcam",
            "Tablet",
            "Smartphone",
            "Docking Station"
    };

    private static final String[] STATUSES = {
            "COMPLETED",
            "SHIPPED",
            "PROCESSING",
            "CANCELLED"
    };

    @Bean
    CommandLineRunner generateOrders(OrderRepository repository) {

        return args -> {

            if (repository.count() > 0) {
                System.out.println("Orders already exist. Skipping data generation.");
                return;
            }

            System.out.println("==========================================");
            System.out.println("Generating " + NUMBER_OF_ORDERS + " orders...");
            System.out.println("==========================================");

            Random random = new Random(42);

            List<OrderEntity> batch = new ArrayList<>(BATCH_SIZE);

            for (int i = 1; i <= NUMBER_OF_ORDERS; i++) {

                String orderId =
                        "ORD-" + String.format("%06d", i);

                String customerId =
                        "CUST-" + (1000 + random.nextInt(1000));

                LocalDate orderDate =
                        LocalDate.of(2026, 10, 1)
                                .minusDays(random.nextInt(30));

                String productName =
                        PRODUCTS[random.nextInt(PRODUCTS.length)];

                String productCode =
                        "PROD-" + String.format(
                                "%03d",
                                random.nextInt(10) + 1
                        );

                int quantity =
                        random.nextInt(10) + 1;

                BigDecimal unitPrice =
                        BigDecimal.valueOf(
                                50 + (random.nextDouble() * 1950)
                        ).setScale(2, RoundingMode.HALF_UP);

                BigDecimal totalAmount =
                        unitPrice
                                .multiply(BigDecimal.valueOf(quantity))
                                .setScale(2, RoundingMode.HALF_UP);

                String status =
                        STATUSES[random.nextInt(STATUSES.length)];

                OrderEntity order =
                        new OrderEntity(
                                orderId,
                                customerId,
                                orderDate,
                                productCode,
                                productName,
                                quantity,
                                unitPrice,
                                totalAmount,
                                status
                        );

                batch.add(order);

                if (batch.size() == BATCH_SIZE) {

                    repository.saveAll(batch);
                    batch.clear();

                    if (i % 1000 == 0) {
                        System.out.println(
                                "Generated " + i + " orders..."
                        );
                    }
                }
            }

            if (!batch.isEmpty()) {
                repository.saveAll(batch);
            }

            System.out.println("==========================================");
            System.out.println("ORDER DATA GENERATION COMPLETE");
            System.out.println("Total orders: " + repository.count());
            System.out.println("==========================================");
        };
    }
}