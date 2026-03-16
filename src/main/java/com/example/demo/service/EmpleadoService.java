package com.example.demo.service;

import com.example.demo.dto.request.EmpleadoRequestDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;

import java.util.List;

public interface EmpleadoService {

    EmpleadoResponseDTO actualizar(Long id, EmpleadoRequestDTO dto);

    void eliminar(Long id);

    EmpleadoResponseDTO crear(EmpleadoRequestDTO dto);

    List<EmpleadoResponseDTO> listar();

    EmpleadoResponseDTO buscarPorId(Long id);

}
