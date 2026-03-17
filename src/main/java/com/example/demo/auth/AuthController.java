package com.example.demo.auth;

import com.example.demo.config.JwtService;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.model.Empleado;
import com.example.demo.model.Persona;
import com.example.demo.repository.EmpleadoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final EmpleadoRepository empleadoRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        Empleado empleado = empleadoRepository.findByUsuario(request.usuario())
                .orElseThrow(() -> new BusinessRuleException("Usuario no encontrado"));

        if (!empleado.getContrasena().equals(request.contrasena())) {
            throw new BusinessRuleException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(
                empleado.getUsuario(),
                empleado.getRol().name()
        );

        return ResponseEntity.ok(
                new LoginResponse(token, empleado.getRol().name())
        );
    }

    @Operation(
            summary = "Registrar usuario",
            description = "Crea un nuevo empleado y retorna un token JWT"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario ya existe")
    })
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest request) {

        if (empleadoRepository.findByUsuario(request.usuario()).isPresent()) {
            throw new BusinessRuleException("El usuario ya existe");
        }

        Persona persona = new Persona();
        persona.setNombre(request.nombre());
        persona.setDocumento(request.documento());
        persona.setCorreo(request.correo());
        persona.setTelefono(request.telefono());

        Empleado empleado = new Empleado();
        empleado.setPersona(persona);
        empleado.setUsuario(request.usuario());
        empleado.setContrasena(request.contrasena());
        empleado.setRol(request.rol());

        empleadoRepository.save(empleado);

        String token = jwtService.generateToken(
                empleado.getUsuario(),
                empleado.getRol().name()
        );

        return ResponseEntity.ok(
                new LoginResponse(token, empleado.getRol().name())
        );
    }
}