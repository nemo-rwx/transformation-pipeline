package com.dataupload.sftpupload.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;

@Service
public class S3StorageService {

    private final S3Client s3Client;
    private final String bucketName;

    public S3StorageService(
            S3Client s3Client,
            @Value("${app.s3.bucket-name}") String bucketName) {

        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public void uploadFile(String objectKey, Path filePath) {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromFile(filePath)
        );

        System.out.println(
                "File uploaded to S3: s3://" + bucketName + "/" + objectKey
        );
    }
}