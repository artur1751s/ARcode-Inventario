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
        return proveedoresRepository.findById(id).map(proveedor -> {
            proveedor.setNombre(proveedores.getNombre());
            proveedor.setIdentificacion(proveedores.getIdentificacion());
            proveedor.setCorreo(proveedores.getCorreo());
            proveedor.setTelefono(proveedores.getTelefono());
            proveedor.setEmpresa(proveedores.getEmpresa());
            
            return proveedoresRepository.save(proveedor);
        })
        .orElse(null);
    }

    @PutMapping("/{id}/estado")
    public Proveedores actualizarEstado(@PathVariable Long id, @RequestBody String estado) {
        return proveedoresRepository.findById(id).map(proveedor -> {
            proveedor.setEstado(estado);
            
            return proveedoresRepository.save(proveedor);
        })
        .orElse(null);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        proveedoresRepository.deleteById(id);
    }
}