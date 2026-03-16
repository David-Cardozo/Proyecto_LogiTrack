package com.example.demo.controller;

import com.example.demo.dto.request.MovimientosDetalleRequestDTO;
import com.example.demo.dto.response.MovimientosDetalleResponseDTO;
import com.example.demo.service.impl.MovimientosDetalleServiceImpl;
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
@RequestMapping("/api/movimientos-detalle")
@RequiredArgsConstructor
public class MovimientosDetalleController {

    private final MovimientosDetalleServiceImpl detalleMovimientoService;

    @Operation(
            summary = "Registrar detalle de movimiento",
            description = "Registra los productos y cantidades involucrados en un movimiento de inventario"
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Detalle de movimiento creado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o body mal estructurado",
                    content = @Content
            )
    })
    public ResponseEntity<MovimientosDetalleResponseDTO> guardar(@Valid @RequestBody MovimientosDetalleRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleMovimientoService.crear(dto));
    }

    @Operation(
            summary = "Actualizar detalle de movimiento",
            description = "Permite actualizar la información de un detalle de movimiento existente mediante su ID"
    )
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Detalle de movimiento actualizado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Detalle de movimiento no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content
            )
    })
    public ResponseEntity<MovimientosDetalleResponseDTO> actualizar(@Valid @RequestBody MovimientosDetalleRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok().body(detalleMovimientoService.actualizar(id, dto));
    }

    @Operation(
            summary = "Buscar detalle de movimiento por ID",
            description = "Obtiene la información de un detalle de movimiento específico mediante su ID"
    )
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Detalle de movimiento encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Detalle de movimiento no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<MovimientosDetalleResponseDTO> buscarId(@PathVariable Long id) {
        return ResponseEntity.ok().body(detalleMovimientoService.buscarPorId(id));
    }

    @Operation(
            summary = "Eliminar detalle de movimiento",
            description = "Permite eliminar un detalle de movimiento del sistema mediante su ID"
    )
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Detalle de movimiento eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Detalle de movimiento no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleMovimientoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Consultar detalles de movimiento",
            description = "Obtiene todos los detalles asociados a un movimiento específico mediante el ID del movimiento"
    )
    @GetMapping("/movimiento/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Detalles de movimiento encontrados"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movimiento no encontrado o sin detalles",
                    content = @Content
            )
    })
    public ResponseEntity<List<MovimientosDetalleResponseDTO>> buscarPorMovimientoId(@PathVariable Long id) {
        return ResponseEntity.ok().body(detalleMovimientoService.buscarPorMovimientoId(id));
    }
}

