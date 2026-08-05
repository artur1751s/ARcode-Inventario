package com.arcode.arcode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @NotBlank(message = "!El nombre es obligatorio!")
    private String nombre;
    @NotBlank(message = "!Debes de ingresar un correo!")
    private String correo;
    @NotBlank(message = "!Debes de ingresar tu numero telefonico!")
    private String telefono;
    @NotBlank(message = "!La contraseña es obligatorio!")
    private String contrasena;
    @NotNull(message = "!Debes de ingresar tu identificacion!")
    private Integer identificacion;
    @NotBlank(message = "!Debes de ingresar tu direccion de residencia!")
    private String direccion;
    private Long rol;
}