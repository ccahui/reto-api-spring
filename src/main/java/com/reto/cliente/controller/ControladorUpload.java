package com.reto.cliente.controller;

import com.reto.cliente.dto.EncriptarUtilDto;
import com.reto.cliente.dto.UploadCreateDto;
import com.reto.cliente.dto.UploadDto;
import com.reto.cliente.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(ControladorUpload.Upload)
@RequiredArgsConstructor
public class ControladorUpload {
    public static final String Upload = "/cargar-archivos";
    private final UploadService uploadService;

    @PostMapping
    public UploadDto uploadDto(@Valid UploadCreateDto uploadDto) {

        if (uploadDto.getFile() != null) {
            return uploadService.upload(uploadDto.getFile());
        }
        return null;
    }

    @GetMapping("/{blobName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String blobName) {
        return uploadService.dowload(blobName);
    }
}