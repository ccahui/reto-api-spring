package com.reto.cliente.dto;

import com.reto.cliente.validation.FileSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadCreateDto {


    @NotBlank
    private String nombre;
    @NotNull
    @FileSize
    private MultipartFile file;
}
