package com.dataupload.sftpupload.transformation;

import com.dataupload.sftpupload.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderCsvGenerator {

    public String generate(List<OrderEntity> orders) {

        StringBuilder csv = new StringBuilder();

        csv.append("order_id,customer_id,order_date,")
                .append("product_code,product_name,quantity,")
                .append("unit_price,total_amount,status\n");

        for (OrderEntity order : orders) {

            csv.append(order.getOrderId()).append(",")
                    .append(order.getCustomerId()).append(",")
                    .append(order.getOrderDate()).append(",")
                    .append(order.getProductCode()).append(",")
                    .append(order.getProductName()).append(",")
                    .append(order.getQuantity()).append(",")
                    .append(order.getUnitPrice()).append(",")
                    .append(order.getTotalAmount()).append(",")
                    .append(order.getStatus())
                    .append("\n");
        }

        return csv.toString();
    }
}