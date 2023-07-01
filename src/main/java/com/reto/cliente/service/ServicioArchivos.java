package com.reto.cliente.service;

import com.reto.cliente.dto.ActualizarClienteDto;
import com.reto.cliente.dto.ClienteDto;

import java.util.List;

public interface ServicioArchivos {

    List<ClienteDto> listar();
    ClienteDto buscarPorCodigo(String codigo);
    ClienteDto actualizar(String codigo, ActualizarClienteDto clienteDto);

    ClienteDto buscarPorCodigoEncriptado(String codigoEncriptado);
}
