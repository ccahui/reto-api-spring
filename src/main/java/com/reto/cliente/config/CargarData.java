package com.reto.cliente.config;

import com.reto.cliente.entity.Cliente;
import com.reto.cliente.repository.RepositorioCliente;
import com.reto.cliente.utils.TipoDocumento;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class CargarData implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(CargarData.class);
    private final RepositorioCliente clienteRepository;


    @Override
    public void run(String... args) throws Exception {

        Cliente clienteA = new Cliente().builder().codigoUnico("123456").nombre("Kristian").apellidos("Juan").tipoDocumento(TipoDocumento.DNI).numeroDocumento("12345678").build();



        clienteRepository.save(clienteA);

        log.info("Cliente Creados " + clienteRepository.findAll());
    }
}
