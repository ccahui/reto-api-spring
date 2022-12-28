package com.reto.cliente.service;

import com.reto.cliente.dto.ActualizarClienteDto;
import com.reto.cliente.dto.ClienteDto;
import com.reto.cliente.entity.Cliente;
import com.reto.cliente.exceptions.NotFoundException;
import com.reto.cliente.mapper.MapperCliente;
import com.reto.cliente.repository.RepositorioCliente;
import com.reto.cliente.utils.CifrarRSA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
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
    @Mock
    private CifrarRSA cifrar;
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

    @Test
    void actualizar_nombre_y_apellido_valido() {

        String codigoUnico = "123456";
        ActualizarClienteDto actualizarClienteDto = ActualizarClienteDto.builder()
                .nombre("Jose2").apellidos("Carlos2")
                .build();

        Cliente cliente = new Cliente().builder().codigoUnico(codigoUnico).build();
        ClienteDto clienteDtoResponse = new ClienteDto();


        when(repositorioCliente.findByCodigoUnico(codigoUnico)).thenReturn(Optional.of(cliente));
        when(mapperCliente.entityToDto(cliente)).thenReturn(clienteDtoResponse);

        servicioCliente.actualizar(codigoUnico, actualizarClienteDto);

        ArgumentCaptor<Cliente> clienteCaptor = ArgumentCaptor.forClass(Cliente.class);

        verify(repositorioCliente).findByCodigoUnico(codigoUnico);
        verify(repositorioCliente).save(clienteCaptor.capture());
        verify(mapperCliente).entityToDto(cliente);

        assertEquals(actualizarClienteDto.getNombre(), clienteCaptor.getValue().getNombre());
        assertEquals(actualizarClienteDto.getApellidos(), clienteCaptor.getValue().getApellidos());

    }

    @Test
    void buscar_codigo_encriptado_valido() {

        String codigoEncriptado = "##$WQ$DAFAD";
        String codigoUnico = "123456";
        Cliente cliente = new Cliente().builder().codigoUnico(codigoUnico).build();
        ClienteDto clienteDto = new ClienteDto();

        when(cifrar.desencriptar(codigoEncriptado)).thenReturn(codigoUnico);
        when(repositorioCliente.findByCodigoUnico(codigoUnico)).thenReturn(Optional.of(cliente));
        when(mapperCliente.entityToDto(cliente)).thenReturn(clienteDto);

        servicioCliente.buscarPorCodigoEncriptado(codigoEncriptado);

        verify(cifrar).desencriptar(codigoEncriptado);
        verify(repositorioCliente).findByCodigoUnico(codigoUnico);
        verify(mapperCliente).entityToDto(cliente);

    }
}