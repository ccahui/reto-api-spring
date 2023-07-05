package com.reto.cliente.service;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobDownloadResponse;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlobProperties;
import com.azure.storage.blob.options.BlobUploadFromFileOptions;
import com.reto.cliente.dto.UploadDto;
import com.reto.cliente.exceptions.UploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UploadAzureServiceImpl implements UploadService {

    private final AzureUploadConfig azureUploadConfig;
    Logger logger = Logger.getLogger(UploadAzureServiceImpl.class.getName());
    public UploadDto upload(MultipartFile file) {


        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(azureUploadConfig.getConnectionString()).buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(azureUploadConfig.getContainerName());

        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        BlobHttpHeaders blobHttpHeader = new BlobHttpHeaders();
        blobHttpHeader.setContentType(file.getContentType());

        try {
            blobClient.upload(file.getInputStream(),  file.getSize());
            blobClient.setHttpHeaders(blobHttpHeader);
        } catch (IOException e) {
            throw new UploadException(String.format("File %s: %s", file.getName(), e.getMessage()));
        }
        return UploadDto.builder()
                .url(blobClient.getBlobUrl().toString())
                .name(blobClient.getBlobName()).build();


    }

    @Override
    public ResponseEntity<Resource> dowload(String blobName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(azureUploadConfig.getConnectionString()).buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(azureUploadConfig.getContainerName());

        BlobClient blobClient = containerClient.getBlobClient(blobName);

        ByteArrayInputStream blobStream = new ByteArrayInputStream(blobClient.downloadContent().toBytes());

        // Obtener las propiedades del blob
        BlobProperties properties = blobClient.getProperties();

        // Crear un recurso de flujo de entrada a partir del blobStream
        Resource resource = new InputStreamResource(blobStream);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(properties.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + blobName + "\"")
                .body(resource);
    }
}
