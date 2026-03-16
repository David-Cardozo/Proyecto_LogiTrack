package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductoRequestDTO(

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        @Schema(description = "Nombre del producto", example = "Rack metálico industrial")
        String nombre,

        @NotBlank(message = "La categoria no puede estar vacía")
        @Size(min = 3, max = 30, message = "La categoria debe tener entre 3 y 30 caracteres")
        @Schema(description = "Categoría del producto", example = "Estructuras de almacenamiento")
        String categoria,

        @NotNull(message = "El tamaño no puede estar vacío")
        @Schema(description = "Tamaño del producto(Pequeño,Mediano,Grande)", example = "Grande")
        com.example.demo.enums.Size tamano,

        @NotNull(message = "El precio mensual no puede ser nulo")
        @Positive(message = "El precio debe ser mayor que 0")
        @Schema(description = "Precio mensual del producto", example = "325000")
        BigDecimal precioMensual

) {
}