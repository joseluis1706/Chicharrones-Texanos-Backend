package com.texanos.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
    private Integer stock;
    private Boolean activo;
}
