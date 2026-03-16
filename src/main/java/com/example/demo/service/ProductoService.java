package com.example.demo.service;

import com.example.demo.dto.request.ProductoRequestDTO;
import com.example.demo.dto.response.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoResponseDTO> listar();

    ProductoResponseDTO crear(ProductoRequestDTO dto);

    void eliminar(Long id);

    ProductoResponseDTO buscarPorId(Long id);


    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto);

}
