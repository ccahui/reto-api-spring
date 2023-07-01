package com.reto.cliente.service;

import com.reto.cliente.dto.ActualizarClienteDto;
import com.reto.cliente.dto.ClienteDto;
import com.reto.cliente.entity.Cliente;
import com.reto.cliente.exceptions.NotFoundException;
import com.reto.cliente.mapper.MapperCliente;
import com.reto.cliente.repository.RepositorioCliente;
import com.reto.cliente.utils.Cifrar;
import com.reto.cliente.validation.FileSizeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicioClienteImpl implements ServicioCliente {

    private final RepositorioCliente repositorioCliente;
    private final MapperCliente mapperCliente;
    private final Cifrar cifrar;

    @Override
    public List<ClienteDto> listar() {
        List<Cliente> categories = repositorioCliente.findAll();
        List<ClienteDto> dtos = categories.stream().map(mapperCliente::entityToDto).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public ClienteDto buscarPorCodigo(String codigo) {
        Cliente cliente = repositorioCliente.findByCodigoUnico(codigo).orElseThrow(() -> new NotFoundException("Cliente codigoUnico (" + codigo + ")"));
        return mapperCliente.entityToDto(cliente);
    }

    @Override
    public ClienteDto buscarPorCodigoEncriptado(String codigoEncriptado) {
        String codigo = cifrar.desencriptar(codigoEncriptado);
        Cliente cliente = repositorioCliente.findByCodigoUnico(codigo).orElseThrow(() -> new NotFoundException("Cliente codigoUnico (" + codigo + ")"));
        return mapperCliente.entityToDto(cliente);
    }
    @Override
    public ClienteDto actualizar(String codigo, ActualizarClienteDto clienteDto) {
        Cliente cliente = repositorioCliente.findByCodigoUnico(codigo).orElseThrow(() -> new NotFoundException("Cliente codigoUnico (" + codigo + ")"));

        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        repositorioCliente.save(cliente);

        return mapperCliente.entityToDto(cliente);

    }
}
