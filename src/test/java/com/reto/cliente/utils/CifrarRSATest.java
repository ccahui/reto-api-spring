package com.reto.cliente.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.jupiter.api.Assertions.*;

class CifrarRSATest {

    private final Logger log = LoggerFactory.getLogger(CifrarRSATest.class);
    private CifrarRSA cifrarRSA;
    @BeforeEach
    void inicializar() throws Exception{
        cifrarRSA = new CifrarRSA();
    }
    @Test
    void encriptar() {
        String codigo = "123456";

        String mensajeCifrado = cifrarRSA.encriptar(codigo);
        log.info("Mensaje Cifrado: "+ mensajeCifrado );

        assertNotEquals(codigo, mensajeCifrado);
        assertTrue(mensajeCifrado.length() > 30);
    }
    @Test
    void desencriptar() {
        String codigo = "123456";
        String mensajeCifrado = cifrarRSA.encriptar(codigo);

        String mensajeDescifrado = cifrarRSA.desencriptar(mensajeCifrado);
        log.info("Mensaje Cifrado: "+ mensajeCifrado );
        log.info("Mensaje Descifrado: "+ mensajeDescifrado );

        assertEquals(codigo, mensajeDescifrado);

    }


}