package com.example.demo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record MovimientosDetalleResponseDTO(

        @Schema(description = "ID del detalle del movimiento", example = "28")
        Long id,

        @Schema(description = "Información del movimiento asociado")
        MovimientosResponseDTO idMovimiento,

        @Schema(description = "Información del producto asociado al movimiento")
        ProductoResponseDTO idProducto,

        @Schema(description = "Cantidad del producto en el movimiento", example = "75")
        Integer cantidad
) {
}
