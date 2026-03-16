package com.example.demo.service;

import com.example.demo.dto.request.InventarioRequestDTO;
import com.example.demo.dto.response.InventarioResponseDTO;

import java.util.List;

public interface InventarioService {

    InventarioResponseDTO crear(InventarioRequestDTO dto);


    InventarioResponseDTO actualizar(Long id, InventarioRequestDTO dto);

    void eliminar(Long id);

    List<InventarioResponseDTO> listar();

    InventarioResponseDTO buscarPorId(Long id);
}
