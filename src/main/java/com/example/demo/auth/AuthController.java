package com.example.demo.auth;

import com.example.demo.config.JwtService;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final EmpleadoRepository empleadoRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        Empleado empleado = empleadoRepository.findByUsuario(request.usuario());

        if (empleado == null) {
            throw new BusinessRuleException("No se encontro el User");
        }

        if (!empleado.getContrasena().equals(request.contrasena())) {
            throw new BusinessRuleException("Login Invalido, revise credenciales");
        }

        String token = jwtService.generateToken(empleado.getUsuario(), empleado.getRol().name());

        return ResponseEntity.ok(new LoginResponse(token, empleado.getRol().name()));
    }

}

