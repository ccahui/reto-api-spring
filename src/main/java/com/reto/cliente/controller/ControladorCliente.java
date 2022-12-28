package com.reto.cliente.controller;

import com.reto.cliente.dto.ActualizarClienteDto;
import com.reto.cliente.dto.ClienteDto;
import com.reto.cliente.service.ServicioCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(ControladorCliente.CLIENTES)
@RequiredArgsConstructor
public class ControladorCliente {
    public static final String CLIENTES = "/clientes";
    private final ServicioCliente servicioCliente;


    @GetMapping
    public List<ClienteDto> listar() {
        return servicioCliente.listar();
    }

    @GetMapping(value = {"/{codigo}"})
    public ClienteDto buscar(@PathVariable String codigo) {
        return servicioCliente.buscarPorCodigo(codigo);
    }

    @PostMapping(value = {"/{codigo}"})
    public ClienteDto create(@PathVariable String codigo, @Valid @RequestBody ActualizarClienteDto cliente) {
        return servicioCliente.actualizar(codigo, cliente);
    }

    @GetMapping(value = {"/encriptado/{codigoEncriptado}"})
    public ClienteDto buscarPorCodigoEncriptado(@PathVariable String codigoEncriptado) {
        return servicioCliente.buscarPorCodigoEncriptado(codigoEncriptado);
    }




}
