package com.reto.cliente.controller;

import com.reto.cliente.dto.EncriptarUtilDto;
import com.reto.cliente.dto.UploadCreateDto;
import com.reto.cliente.dto.UploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(ControladorUpload.Upload)
@RequiredArgsConstructor
public class ControladorUpload {
    public static final String Upload = "/cargar-archivos";
    @PostMapping
    public UploadDto uploadDto(@Valid UploadCreateDto uploadDto) {

        if(uploadDto.getFile()!=null){
            return UploadDto.builder().url(uploadDto.getFile().getName()).build();
        }
        return null;
    }
}
