package com.example.demo.dto.request;

import com.example.demo.enums.MovimientoTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record MovimientosRequestDTO(

        @NotNull(message = "La fecha del movimiento es obligatoria")
        @Schema(description = "Fecha en la que se realiza el movimiento", example = "2026-07-03")
        LocalDate fecha,

        @NotNull(message = "El tipo de movimiento es obligatorio")
        @Schema(description = "Tipo de movimiento realizado(SALIDA/ENTRADA/TRANSFERENCIA)", example = "ENTRADA")
        MovimientoTipo tipoMovimiento,

        @NotNull(message = "El id del empleado es obligatorio")
        @Positive(message = "El id del empleado debe ser mayor que 0")
        @Schema(description = "ID del empleado que realiza el movimiento", example = "9")
        Long idEmpleado,

        @NotNull(message = "El id de la bodega origen es obligatorio")
        @Positive(message = "El id de la bodega origen debe ser mayor que 0")
        @Schema(description = "ID de la bodega desde donde salen los productos", example = "4")
        Long idBodegaOrigen,

        @NotNull(message = "El id de la bodega destino es obligatorio")
        @Positive(message = "El id de la bodega destino debe ser mayor que 0")
        @Schema(description = "ID de la bodega hacia donde se envían los productos", example = "7")
        Long idBodegaDestino

) {
}
