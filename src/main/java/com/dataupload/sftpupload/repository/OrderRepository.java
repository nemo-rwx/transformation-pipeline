package com.dataupload.sftpupload.repository;

import com.dataupload.sftpupload.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCustomerIdAndOrderDate(
            String customerId,
            LocalDate orderDate
    );
}