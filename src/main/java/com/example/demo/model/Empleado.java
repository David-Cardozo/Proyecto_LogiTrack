package com.example.demo.model;

import com.example.demo.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empleado")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empleado extends Persona {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles rol;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String contrasena;
}