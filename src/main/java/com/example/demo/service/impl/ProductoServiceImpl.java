package com.example.demo.service.impl;

import com.example.demo.dto.request.ProductoRequestDTO;
import com.example.demo.dto.response.ProductoResponseDTO;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.mapper.ProductoMapper;
import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        Producto p = productoMapper.DTOAentidad(dto);
        Producto productoInsertado = productoRepository.save(p);

        return productoMapper.entidadADTO(productoInsertado);
    }

    @Override
    public List<ProductoResponseDTO> listar() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::entidadADTO)
                .toList();
    }

    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Producto sin existencias"));

        return productoMapper.entidadADTO(p);
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Producto Inexistente"));

        productoMapper.actualizarEntidadDesdeDTO(p, dto);

        Producto productoActualizado = productoRepository.save(p);

        return productoMapper.entidadADTO(productoActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Producto Inexistente"));

        productoRepository.delete(p);
    }
}

