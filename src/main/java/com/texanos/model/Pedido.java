package com.texanos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private Double subtotal;
    private Double costoDomicilio;
    private Double total;

    private String estado; // PENDIENTE, EN_PROCESO, ENTREGADO

    private String nombreCliente;
    private String telefono;
    private String direccion;
    private String metodoPago;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DetallePedido> detalles;

    @Column(unique = true)
    private String codigo;
}
