package com.example.demo.service;

import com.example.demo.dto.request.BodegaRequestDTO;
import com.example.demo.dto.response.BodegaResponseDTO;

import java.util.List;

public interface BodegaService {

    BodegaResponseDTO crear(BodegaRequestDTO dto);

    BodegaResponseDTO actualizar(Long id, BodegaRequestDTO dto);

    void eliminar(Long id);

    List<BodegaResponseDTO> listar();

    BodegaResponseDTO buscarPorId(Long id);

}
