package com.example.demo.dto.response;

import com.example.demo.enums.Size;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductoResponseDTO(

        @Schema(description = "ID del producto", example = "91")
        Long id,

        @Schema(description = "Nombre del producto", example = "Contenedor modular industrial")
        String nombre,

        @Schema(description = "Categoría del producto", example = "Almacenamiento pesado")
        String categoria,

        @Schema(description = "Tamaño del producto", example = "Grande")
        Size tamano,

        @Schema(description = "Precio mensual del producto", example = "247500")
        BigDecimal precioMensual
) {
}
