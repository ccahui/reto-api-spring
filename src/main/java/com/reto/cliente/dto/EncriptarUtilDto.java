package com.reto.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncriptarUtilDto {
    private String algoritmo;
    private String clavePublica;
    private String clavePrivada;
    private String textoPlano;
    private String textoEncryptado;
}
