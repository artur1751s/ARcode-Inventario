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

import com.arcode.arcode.model.Proveedores;
import com.arcode.arcode.repository.ProveedoresRepository;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedoresController {
    @Autowired
    private ProveedoresRepository proveedoresRepository;

    @GetMapping
    public List<Proveedores> getAll() {
        return proveedoresRepository.findAll();
    }

    @GetMapping("/{id}")
    public Proveedores getById(@PathVariable Long id) {
        return proveedoresRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Proveedores create(@RequestBody Proveedores proveedores) {
        return proveedoresRepository.save(proveedores);
    }

    @PutMapping("/{id}")
    public Proveedores update(@PathVariable Long id, @RequestBody Proveedores proveedores) {
        proveedores.setId_proveedor(id);
        return proveedoresRepository.save(proveedores);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        proveedoresRepository.deleteById(id);
    }
}