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
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @NotBlank(message = "!Debes de ingresar el nombre de un producto!")
    private String nombre;
    @NotBlank(message = "!Debes de ingresar una descripcion!")
    private String descripcion;
    @NotNull(message = "!Debes de ingresar a que categoria pertenece el producto!")
    private String categoria;
    @NotNull(message = "!Debes de ingresar el precio del producto!")
    private Double precio;
    @NotNull(message = "!El stock es obligatorio!")
    private Integer stock;
    @NotNull(message = "!Debes de ingresar la id de la bodega!")
    private Long id_bodega; //llave foranea de bodega
}