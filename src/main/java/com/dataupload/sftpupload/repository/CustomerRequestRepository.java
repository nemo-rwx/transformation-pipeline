package com.dataupload.sftpupload.repository;

import com.dataupload.sftpupload.entity.CustomerRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRequestRepository
        extends JpaRepository<CustomerRequestEntity, Long> {

    Optional<CustomerRequestEntity> findByRequestId(String requestId);
}