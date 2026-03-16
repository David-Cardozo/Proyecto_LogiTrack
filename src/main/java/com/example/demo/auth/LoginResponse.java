package com.example.demo.auth;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
        @Schema(description = "Token de acceso al sistema")
        String token,

        @Schema(description = "El Rol es:", example = "ADMIN")
        String rol
) {
}
