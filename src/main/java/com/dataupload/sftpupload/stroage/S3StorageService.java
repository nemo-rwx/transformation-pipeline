package com.dataupload.sftpupload.stroage;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;

@Service
public class S3StorageService {

    private final S3Client s3Client;

    public S3StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(
            String bucketName,
            String objectKey,
            Path filePath) {

        PutObjectRequest request =
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(objectKey)
                        .build();

        s3Client.putObject(
                request,
                RequestBody.fromFile(filePath)
        );

        System.out.println(
                "File uploaded to S3: "
                        + objectKey
        );
    }
}