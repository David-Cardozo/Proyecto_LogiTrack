package com.example.demo.controller;

import com.example.demo.dto.request.BodegaRequestDTO;
import com.example.demo.dto.response.BodegaResponseDTO;
import com.example.demo.service.impl.BodegaServiceImpl;
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
@RequestMapping("/api/bodegas")
@RequiredArgsConstructor
public class BodegaController {

    private final BodegaServiceImpl bodegaService;

    @Operation(
            summary = "Registrar bodega",
            description = "Registra una nueva bodega dentro del sistema de gestión de bodegas de LogiTrack"
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bodega registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    public ResponseEntity<BodegaResponseDTO> guardar(@Valid @RequestBody BodegaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bodegaService.crear(dto));
    }

    @Operation(
            summary = "Actualizar bodega",
            description = "Actualiza la información de una bodega existente mediante su identificador"
    )
    @PutMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO> actualizar(@Valid @RequestBody BodegaRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok().body(bodegaService.actualizar(id, dto));
    }

    @Operation(
            summary = "Consultar bodegas",
            description = "Obtiene la lista completa de bodegas registradas en el sistema"
    )
    @GetMapping
    public ResponseEntity<List<BodegaResponseDTO>> listarTodos() {
        return ResponseEntity.ok().body(bodegaService.listar());
    }

    @Operation(
            summary = "Consultar bodega por ID",
            description = "Obtiene la información detallada de una bodega específica"
    )
    @GetMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO> buscarId(@PathVariable Long id) {
        return ResponseEntity.ok().body(bodegaService.buscarPorId(id));
    }

    @Operation(
            summary = "Eliminar bodega",
            description = "Elimina una bodega del sistema mediante su identificador"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bodegaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}