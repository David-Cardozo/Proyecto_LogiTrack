package com.example.demo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record BodegaResponseDTO(
        @Schema(description = "ID de la bodega", example = "1")
        Long id,

        @Schema(description = "Nombre de la bodega", example = "Bodega Central")
        String nombre,

        @Schema(description = "Ubicación de la bodega", example = "Bogotá - Zona Industrial")
        String ubicacion,

        @Schema(description = "Capacidad máxima de almacenamiento de la bodega", example = "500")
        Integer capacidad,

        @Schema(description = "Empleado encargado de la bodega")
        EmpleadoResponseDTO idEncargado
) {
}
