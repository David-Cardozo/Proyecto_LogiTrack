package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;


public record BodegaRequestDTO(

        @NotBlank(message = "No se permite valores vacios este espacio")
        @Size(min = 2, max = 50, message = "El nombre debe tener un minimo de 2 a 50 caracteres")
        @Schema(description = "Nombre de la bodega", example = "Centro Logístico Andino")
        String nombre,

        @NotBlank(message = "No se permite valores vacios en este espacio")
        @Size(min = 2, max = 100, message = "Minimo de 2 a 100 caracteres")
        @Schema(description = "Ubicación física de la bodega", example = "Medellín - Parque Industrial Sur")
        String ubicacion,

        @NotNull(message = "No se permite valores vacios en este espacio")
        @PositiveOrZero(message = "No se aceptan numeros negativos")
        @Schema(description = "Capacidad Disponible de almacenamiento de la bodega", example = "720")
        Integer capacidad,

        @NotNull(message = "No se permite valores vacios en este spacio")
        @Positive(message = "No se aceptan numeros negativos")
        @Schema(description = "ID del empleado encargado de la bodega", example = "11")
        Long idEncargado

) {
}
