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

import com.arcode.arcode.model.Pedidos;
import com.arcode.arcode.repository.PedidosRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {
    @Autowired
    private PedidosRepository pedidosRepository;

    @GetMapping
    public List<Pedidos> getAll() {
        return pedidosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedidos getById(@PathVariable Long id) {
        return pedidosRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Pedidos create(@RequestBody Pedidos pedidos) {
        return pedidosRepository.save(pedidos);
    }

    @PutMapping("/{id}")
    public Pedidos update(@PathVariable Long id, @RequestBody Pedidos pedidos) {
        pedidos.setId_pedido(id);
        return pedidosRepository.save(pedidos);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pedidosRepository.deleteById(id);
    }
}