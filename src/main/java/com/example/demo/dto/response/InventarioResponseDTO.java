package com.example.demo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record InventarioResponseDTO(

        @Schema(description = "ID del registro de inventario", example = "42")
        Long id,

        @Schema(description = "Bodega donde se encuentra almacenado el producto")
        BodegaResponseDTO bodega,

        @Schema(description = "Producto almacenado en la bodega")
        ProductoResponseDTO producto,

        @Schema(description = "Cantidad disponible del producto en la bodega", example = "320")
        Integer cantidad
) {
}
