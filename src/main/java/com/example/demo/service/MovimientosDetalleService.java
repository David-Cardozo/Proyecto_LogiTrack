package com.example.demo.service;

import com.example.demo.dto.request.MovimientosDetalleRequestDTO;
import com.example.demo.dto.response.MovimientosDetalleResponseDTO;

import java.util.List;

public interface MovimientosDetalleService {

    MovimientosDetalleResponseDTO crear(MovimientosDetalleRequestDTO dto);

    void eliminar(Long id);

    List<MovimientosDetalleResponseDTO> buscarPorMovimientoId(Long idMovimiento);

    MovimientosDetalleResponseDTO buscarPorId(Long id);

    MovimientosDetalleResponseDTO actualizar(Long id, MovimientosDetalleRequestDTO dto);
}
