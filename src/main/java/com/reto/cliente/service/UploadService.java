package com.reto.cliente.service;

import com.reto.cliente.dto.ActualizarClienteDto;
import com.reto.cliente.dto.ClienteDto;
import com.reto.cliente.dto.UploadDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UploadService {

    public UploadDto upload(MultipartFile file);
    public ResponseEntity<Resource> dowload(String fileName);
}
