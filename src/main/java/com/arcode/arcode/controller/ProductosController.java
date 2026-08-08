package com.arcode.arcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcode.arcode.model.Productos;
import com.arcode.arcode.repository.ProductosRepository;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {
    @Autowired
    private ProductosRepository ProductosRepository;

    @GetMapping
    public List<Productos> getAll() {
        return ProductosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Productos getById(@PathVariable Long id) {
        return ProductosRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Productos create(@RequestBody Productos productos) {
        return ProductosRepository.save(productos);
    }

    @PutMapping("/{id}")
    public Productos update(@PathVariable Long id, @RequestBody Productos productos) {
        productos.setId_producto(id);
        return ProductosRepository.save(productos);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ProductosRepository.deleteById(id);
    }
}