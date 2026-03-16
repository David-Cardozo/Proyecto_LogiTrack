package com.example.demo.service;

import com.example.demo.dto.request.MovimientosDetalleRequestDTO;
import com.example.demo.dto.request.MovimientosRequestDTO;
import com.example.demo.dto.response.MovimientosResponseDTO;
import org.jspecify.annotations.NonNull;

import java.util.List;

public interface MovimientosService {
    MovimientosResponseDTO crear(MovimientosRequestDTO dto);

    List<MovimientosResponseDTO> listar();

    MovimientosResponseDTO buscarPorId(Long id);

    MovimientosResponseDTO actualizar(Long id, MovimientosRequestDTO dto);

    void eliminar(Long id);
}
