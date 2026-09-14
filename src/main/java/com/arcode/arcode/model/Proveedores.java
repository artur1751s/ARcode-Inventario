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
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proveedor;

    @NotBlank(message = "!El nombre es obligatorio!")
    private String nombre;
    @NotBlank(message = "!Debes de ingresar un correo!")
    private String correo;
    @NotNull(message = "!La empresa es obligatorio!")
    private String empresa;
    @NotBlank(message = "!Debes de ingresar tu numero telefonico!")
    private String telefono;
    @NotNull(message = "!Debes de ingresar tu identificacion!")
    private Integer identificacion;
    @NotBlank
    private String estado;
}