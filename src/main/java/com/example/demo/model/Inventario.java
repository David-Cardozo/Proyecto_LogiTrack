package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "inventario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"idBodega", "idProducto"})
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBodega", nullable = false)
    private Bodega idBodega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto idProducto;

    @Column(nullable = false)
    private Integer cantidad;
}
