package com.reto.cliente.repository;

import com.reto.cliente.entity.Cliente;
import com.reto.cliente.utils.TipoDocumento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class RepositorioClienteTest {

    @Autowired
    private RepositorioCliente repositorioCliente;

    @BeforeEach
    void dbInsertData() {
        Cliente clienteA = new Cliente().builder().codigoUnico("123456").nombre("Kristian").apellidos("Juan").tipoDocumento(TipoDocumento.DNI).numeroDocumento("12345678").build();
        repositorioCliente.save(clienteA);
    }

    @AfterEach
    void dbTruncate(){
        repositorioCliente.deleteAll();
    }

    @Test
    void cuandoBuscaPorCodigo123456_retornarClienteKristian() {

        String codigoUnico = "123456";

        Optional<Cliente> cliente = repositorioCliente.findByCodigoUnico(codigoUnico);

        assertTrue(cliente.isPresent());
        assertEquals("Kristian", cliente.get().getNombre());
    }

    @Test
    void cuandoBuscaPorCodigoInvalido999999_retornarClienteVacio() {

        String codigoUnico = "99999";

        Optional<Cliente> cliente = repositorioCliente.findByCodigoUnico(codigoUnico);

        assertFalse(cliente.isPresent());
    }
}