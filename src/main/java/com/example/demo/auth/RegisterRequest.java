package com.example.demo.auth;

import com.example.demo.enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank
        @Size(min = 2, max = 100)
        String nombre,

        @NotBlank
        String documento,

        @Email
        @NotBlank
        String correo,

        @NotBlank
        String telefono,

        @NotBlank
        @Size(min = 4, max = 50)
        String usuario,

        @NotBlank
        @Size(min = 4, max = 100)
        String contrasena,

        Roles rol
) {}