package com.example.demo.controller;

import com.example.demo.dto.request.EmpleadoRequestDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;
import com.example.demo.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Operation(
            summary = "Registrar empleado",
            description = "Registra un nuevo empleado del sistema LogiTrack que podrá operar bodegas y movimientos"
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Empleado creado correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o estructura incorrecta",
                    content = @Content
            )
    })
    public ResponseEntity<EmpleadoResponseDTO> guardar(
            @Valid @RequestBody EmpleadoRequestDTO empleadoDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empleadoService.crear(empleadoDTO));
    }

    @Operation(
            summary = "Actualizar empleado",
            description = "Permite actualizar la información de un empleado y su persona asociada mediante el ID"
    )
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Empleado actualizado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Empleado no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content
            )
    })
    public ResponseEntity<EmpleadoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EmpleadoRequestDTO empleadoDTO) {

        return ResponseEntity.ok(
                empleadoService.actualizar(id, empleadoDTO)
        );
    }

    @Operation(
            summary = "Consultar empleados",
            description = "Obtiene la lista de empleados registrados en el sistema"
    )
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de empleados obtenida correctamente"
            )
    })
    public ResponseEntity<List<EmpleadoResponseDTO>> listar() {
        return ResponseEntity.ok(empleadoService.listar());
    }

    @Operation(
            summary = "Buscar empleado por ID",
            description = "Obtiene la información de un empleado específico mediante su ID"
    )
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Empleado encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Empleado no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<EmpleadoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.buscarPorId(id));
    }

    @Operation(
            summary = "Eliminar empleado",
            description = "Permite eliminar un empleado del sistema mediante su ID"
    )
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Empleado eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Empleado no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

