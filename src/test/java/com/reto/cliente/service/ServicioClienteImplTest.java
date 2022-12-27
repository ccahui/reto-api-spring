package com.reto.cliente.service;

import com.reto.cliente.dto.ClienteDto;
import com.reto.cliente.entity.Cliente;
import com.reto.cliente.exceptions.NotFoundException;
import com.reto.cliente.mapper.MapperCliente;
import com.reto.cliente.repository.RepositorioCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ServicioClienteImplTest {

    @Mock
    private MapperCliente mapperCliente;
    @Mock
    private RepositorioCliente repositorioCliente;
    @InjectMocks
    private ServicioClienteImpl servicioCliente;


    @Test
    void listar() {

        Cliente cliente = new Cliente();
        ClienteDto clienteDto = new ClienteDto();
        when(repositorioCliente.findAll()).thenReturn(Arrays.asList(cliente));
        when(mapperCliente.entityToDto(cliente)).thenReturn(clienteDto);

        servicioCliente.listar();

        verify(repositorioCliente).findAll();
        verify(mapperCliente).entityToDto(cliente);

    }

    @Test
    void buscar_codigo_valido() {

        String codigoUnico = "123456";
        Cliente cliente = new Cliente().builder().codigoUnico(codigoUnico).build();
        ClienteDto clienteDto = new ClienteDto();

        when(repositorioCliente.findByCodigoUnico(codigoUnico)).thenReturn(Optional.of(cliente));
        when(mapperCliente.entityToDto(cliente)).thenReturn(clienteDto);

        servicioCliente.buscarPorCodigo(codigoUnico);

        verify(repositorioCliente).findByCodigoUnico(codigoUnico);
        verify(mapperCliente).entityToDto(cliente);

    }

    @Test
    void buscar_codigo_invalido_generar_error() {

        String codigoUnico = "123456";

        when(repositorioCliente.findByCodigoUnico(codigoUnico)).thenReturn(Optional.empty());

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> servicioCliente.buscarPorCodigo(codigoUnico));
        NotFoundException expectMessage = new NotFoundException("Cliente codigoUnico (" + codigoUnico + ")");

        verify(repositorioCliente).findByCodigoUnico(codigoUnico);
        assertEquals(exception.getMessage(), expectMessage.getMessage());

    }
}