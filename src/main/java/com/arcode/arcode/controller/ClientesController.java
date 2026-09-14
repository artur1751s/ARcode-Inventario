package com.arcode.arcode.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcode.arcode.DTO.LoginDTO;
import com.arcode.arcode.model.Clientes;
import com.arcode.arcode.repository.ClientesRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {
    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping
    public List<Clientes> getAll() {
        return clientesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Clientes getById(@PathVariable Long id) {
        return clientesRepository.findById(id).orElse(null);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO datos) {
    // Buscar el cliente mediante su correo
    Optional<Clientes> cliente = clientesRepository.findByCorreo(datos.getCorreo());

    // Verificar que el cliente exista
    if (cliente.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Error en la autenticación");
    }

    Clientes clienteEncontrado = cliente.get();

    // Verificar que la contraseña sea correcta
    if (!clienteEncontrado.getContrasena().equals(datos.getContrasena())) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Error en la autenticación");
    }

    // Verificar que el cliente se encuentre activo
    if (!"ACTIVO".equals(clienteEncontrado.getEstado())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("El usuario se encuentra suspendido");
    }

    // Si todas las validaciones son correctas
    return ResponseEntity.ok("Autenticación satisfactoria");
}

    @PutMapping("/{id}")
    public Clientes update(@PathVariable Long id, @RequestBody Clientes clientes) {
        return clientesRepository.findById(id).map(cliente -> {
            cliente.setNombre(clientes.getNombre());
            cliente.setIdentificacion(clientes.getIdentificacion());
            cliente.setCorreo(clientes.getCorreo());
            cliente.setTelefono(clientes.getTelefono());
            cliente.setDireccion(clientes.getDireccion());
            
            return clientesRepository.save(cliente);
        })
        .orElse(null);
    }

    @PutMapping("/{id}/estado")
    public Clientes actualizarEstado(@PathVariable Long id, @RequestBody String estado) {
        return clientesRepository.findById(id).map(cliente -> {
            cliente.setEstado(estado);
            
            return clientesRepository.save(cliente);
        })
        .orElse(null);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientesRepository.deleteById(id);
    }
}