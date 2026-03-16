package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalleMovimiento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientosDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMovimiento", nullable = false)
    private Movimientos idMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto idProducto;

    @Column(nullable = false)
    private Integer cantidad;
}

