package com.example.demo.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Este campo no debe quedar vacio")
        @Schema(description = "Username", example = "pedritoCampus")
        String usuario,

        @NotBlank(message = "Este campo de debe quedar vacio")
        @Schema(description = "Password", example = "Contraseña123")
        String contrasena

) {
}
