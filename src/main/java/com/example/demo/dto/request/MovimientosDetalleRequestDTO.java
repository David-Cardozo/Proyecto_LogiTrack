package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimientosDetalleRequestDTO(

        @NotNull(message = "El id del movimiento es obligatorio")
        @Positive(message = "El id del movimiento debe ser mayor que 0")
        @Schema(description = "ID del movimiento al que pertenece el detalle", example = "34")
        Long idMovimiento,

        @NotNull(message = "El id del producto es obligatorio")
        @Positive(message = "El id del producto debe ser mayor que 0")
        @Schema(description = "ID del producto que se mueve entre bodegas", example = "15")
        Long idProducto,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 10, message = "La cantidad debe ser mínimo 10")
        @Schema(description = "Cantidad de productos que se trasladan en el movimiento con un minimo de 10Unidades", example = "65")
        Integer cantidad

) {
}
