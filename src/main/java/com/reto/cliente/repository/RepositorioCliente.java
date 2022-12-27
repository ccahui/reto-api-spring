package com.reto.cliente.repository;

import com.reto.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioCliente  extends JpaRepository<Cliente, Long> {

    public Optional<Cliente> findByCodigoUnico(String codigoUnico);
}
