package com.example.demo.dto.request;

import com.example.demo.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record EmpleadoRequestDTO(

        @Size(min = 2, max = 50, message = "Rango de 2 a 50 caracteres")
        @NotBlank(message = "Este campo no puede estar vacio")
        @Schema(description = "Nombre completo ", example = "Santiago Valderrama Torres")
        String nombre,

        @Size(min = 10, max = 20, message = "Rango de 10 a 20 caracteres")
        @Pattern(regexp = "^[0-9,.']{10,20}$", message = "Rango de 10 a 20 caracteres")
        @NotBlank(message = "Este campo no puede estar vacio")
        @Schema(description = "Documento de identidad", example = "1189076543")
        String documento,

        @NotBlank(message = "Este campo no puede estar vacio")
        @Email(message = "Mal formato del correo")
        @Schema(description = "Correo electrónico", example = "s.valderrama@inventrack.io")
        String correo,

        @Size(min = 10, max = 20, message = "Rango de 10 a 20 caracteres")
        @Pattern(regexp = "^[0-9]{10,20}$", message = "Rango de 10 a 20 caracteres")
        @NotBlank(message = "Este campo no puede estar vacio")
        @Schema(description = "Teléfono de contacto", example = "3187749205")
        String telefono,

        @NotNull(message = "Esta campo no puede estar vacio")
        @Schema(description = "Rol del empleado", example = "EMPLEADO")
        Roles rol,

        @NotBlank(message = "Esta campo no puede estar vacio")
        @Size(min = 5, max = 20, message = "Rango de 5 a 20 caracteres")
        @Schema(description = "Usuario del empleado", example = "svalderrama_ops")
        String usuario,

        @NotBlank(message = "Esta campo no puede estar vacio")
        @Size(min = 8, max = 50, message = "Minimo de 8 a 50 caracteres")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,50}$",
                message = "La contraseña debe tener entre 8 y 50 caracteres, con mayuculas, minusculas y numeros"
        )
        @Schema(description = "Contraseña del usuario con el esquema requerido", example = "LogiTrack2026")
        String contrasena

) {
}
