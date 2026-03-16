package com.example.demo.controller;

import com.example.demo.dto.request.MovimientosRequestDTO;
import com.example.demo.dto.response.MovimientosResponseDTO;
import com.example.demo.service.impl.MovimientosServiceImpl;
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
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientosController {

    private final MovimientosServiceImpl movimientoService;

    @Operation(
            summary = "Registrar movimiento de inventario",
            description = "Registra un movimiento de inventario (ENTRADA, SALIDA o TRANSFERENCIA) realizado entre bodegas"
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Movimiento creado correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o body mal estructurado",
                    content = @Content
            )
    })
    public ResponseEntity<MovimientosResponseDTO> guardar(@Valid @RequestBody MovimientosRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movimientoService.crear(dto));
    }

    @Operation(
            summary = "Actualizar movimiento",
            description = "Permite actualizar la información de un movimiento mediante su ID"
    )
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movimiento actualizado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movimiento no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content
            )
    })
    public ResponseEntity<MovimientosResponseDTO> actualizar(
            @Valid @RequestBody MovimientosRequestDTO dto,
            @PathVariable Long id) {

        return ResponseEntity.ok()
                .body(movimientoService.actualizar(id, dto));
    }

    @Operation(
            summary = "Consultar movimientos",
            description = "Obtiene todos los movimientos de inventario registrados en el sistema"
    )
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de movimientos obtenida correctamente"
            )
    })
    public ResponseEntity<List<MovimientosResponseDTO>> listarTodos() {
        return ResponseEntity.ok()
                .body(movimientoService.listar());
    }

    @Operation(
            summary = "Buscar movimiento por ID",
            description = "Obtiene la información de un movimiento específico mediante su ID"
    )
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movimiento encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movimiento no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<MovimientosResponseDTO> buscarId(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(movimientoService.buscarPorId(id));
    }

    @Operation(
            summary = "Eliminar movimiento",
            description = "Permite eliminar un movimiento del sistema mediante su ID"
    )
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Movimiento eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movimiento no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        movimientoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

