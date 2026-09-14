package com.arcode.arcode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcode.arcode.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long>{
    Optional<Clientes> findByCorreo(String correo);
}