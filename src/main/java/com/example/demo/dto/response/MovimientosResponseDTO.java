package com.example.demo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record MovimientosResponseDTO(

        @Schema(description = "ID del movimiento", example = "63")
        Long id,

        @Schema(description = "Fecha en la que se realizó el movimiento", example = "2026-05-18")
        LocalDate fecha,

        @Schema(description = "Tipo de movimiento realizado", example = "REUBICACION")
        String tipoMovimiento,

        @Schema(description = "Empleado que realizó el movimiento")
        EmpleadoResponseDTO idEmpleado,

        @Schema(description = "Bodega de origen del movimiento")
        BodegaResponseDTO idBodegaOrigen,

        @Schema(description = "Bodega de destino del movimiento")
        BodegaResponseDTO idBodegaDestino

) {
}
