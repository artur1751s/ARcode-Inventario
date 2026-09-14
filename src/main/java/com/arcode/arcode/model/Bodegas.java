package com.arcode.arcode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "bodegas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bodegas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_bodega;

    @NotBlank(message = "!El nombre es obligatorio!")
    private String nombre;
    @NotBlank(message = "!Debes de ingresar una ubicacion!")
    private String ubicacion;
}