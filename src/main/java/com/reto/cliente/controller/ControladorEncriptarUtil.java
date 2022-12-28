package com.reto.cliente.controller;

import com.reto.cliente.dto.EncriptarUtilDto;
import com.reto.cliente.utils.CifrarRSA;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@RestController
@RequiredArgsConstructor
public class ControladorEncriptarUtil {

    private final CifrarRSA cifrarRSA;

    @GetMapping(value = {"/encriptar"})
    public EncriptarUtilDto encriptar(@RequestParam String codigo) {

        EncriptarUtilDto dto = EncriptarUtilDto.builder()
                .algoritmo("RSA")
                .clavePublica(cifrarRSA.publicKeyString())
                .clavePrivada(cifrarRSA.privateKeyString())
                .textoPlano(codigo)
                .textoEncryptado(cifrarRSA.encriptar(codigo))
                .build();

        return dto;
    }
    @GetMapping(value = {"/desencriptar"})
    public EncriptarUtilDto desencriptar(@RequestParam String codigoEncriptado) {

        EncriptarUtilDto dto = EncriptarUtilDto.builder()
                .algoritmo("RSA")
                .clavePublica(cifrarRSA.publicKeyString())
                .clavePrivada(cifrarRSA.privateKeyString())
                .textoPlano(cifrarRSA.desencriptar(codigoEncriptado))
                .textoEncryptado(codigoEncriptado)
                .build();

        return dto;
    }
}
