package com.reto.cliente.config;

import com.reto.cliente.entity.Cliente;
import com.reto.cliente.repository.RepositorioCliente;
import com.reto.cliente.utils.TipoDocumento;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class CargarData implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(CargarData.class);
    private final RepositorioCliente clienteRepository;


    @Override
    public void run(String... args) throws Exception {

        Cliente clienteA = new Cliente().builder().codigoUnico("123456")
                .nombre("Kristian").apellidos("Juan")
                .tipoDocumento(TipoDocumento.DNI).numeroDocumento("12345678").build();

        Cliente clienteB = new Cliente().builder().codigoUnico("654321")
                .nombre("Jose").apellidos("Laura")
                .tipoDocumento(TipoDocumento.CARNET_EXTRANJERIA).numeroDocumento("053456789948").build();

        Cliente clienteC = new Cliente().builder().codigoUnico("111111")
                .nombre("Waldir").apellidos("Davila")
                .tipoDocumento(TipoDocumento.PASAPORTE).numeroDocumento("123456780589").build();

        clienteRepository.saveAll(Arrays.asList(clienteA, clienteB, clienteC));
        log.info("Clientes Creados " + clienteRepository.findAll());
    }
}
