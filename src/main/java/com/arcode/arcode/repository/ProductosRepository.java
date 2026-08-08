package com.arcode.arcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcode.arcode.model.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Long>{}