package com.arcode.arcode.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;
    
    private Long id_cliente; //llave foranea de bodega
    @NotNull(message = "!La fecha del pedido es obligatoria!")
    private Date fecha;
    @NotBlank(message = "!Se debe ingresar en que estado se encuentra el pedido!")
    private String estado_pedido;
}