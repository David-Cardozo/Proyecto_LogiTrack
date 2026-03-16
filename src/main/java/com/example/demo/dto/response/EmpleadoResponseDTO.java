package com.example.demo.dto.response;

import com.example.demo.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;

public record EmpleadoResponseDTO(

        @Schema(description = "ID del empleado", example = "17")
        Long id,

        @Schema(description = "Nombre completo del empleado", example = "Laura Camila Ríos")
        String nombre,

        @Schema(description = "Número de documento del empleado", example = "1098765432")
        String documento,

        @Schema(description = "Correo electrónico del empleado", example = "laura.rios@inventrack.io")
        String correo,

        @Schema(description = "Número de teléfono del empleado", example = "3158897421")
        String telefono,

        @Schema(description = "Rol del empleado dentro del sistema", example = "SUPERVISOR")
        Roles rol
) {
}
