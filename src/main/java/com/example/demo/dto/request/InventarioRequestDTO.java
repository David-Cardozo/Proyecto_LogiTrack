package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InventarioRequestDTO(

        @NotNull(message = "El id de la bodega es obligatorio")
        @Positive(message = "El id de la bodega debe ser mayor que 0")
        @Schema(description = "ID de la bodega donde se encuentra el producto", example = "6")
        Long idBodega,

        @NotNull(message = "El id del producto es obligatorio")
        @Positive(message = "El id del producto debe ser mayor que 0")
        @Schema(description = "ID del producto almacenado en la bodega", example = "27")
        Long idProducto,

        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor que 0")
        @Schema(description = "Cantidad disponible del producto en la bodega", example = "870")
        Integer cantidad

) {
}
