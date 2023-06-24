package com.reto.cliente.controller;

import com.reto.cliente.dto.EncriptarUtilDto;
import com.reto.cliente.dto.InstanceDto;
import com.reto.cliente.utils.CifrarRSA;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@RestController
@RequiredArgsConstructor
public class ControladorEncriptarUtil {

    private final CifrarRSA cifrarRSA;
    @Value("${WEBSITE_INSTANCE_ID:unknown}")
    private String instanceId;
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
    @RequestMapping("/instance-app-service")
    public InstanceDto handleRequest() {
        System.out.println("Solicitud recibida en la instancia: " + instanceId);
        String payload = "Respuesta de la instancia: " + instanceId;
        return InstanceDto.builder()
                .instanceAppService(payload)
                .build();
    }
}
