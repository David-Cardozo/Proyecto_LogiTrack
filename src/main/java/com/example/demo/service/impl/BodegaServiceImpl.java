package com.example.demo.service.impl;

import com.example.demo.dto.request.BodegaRequestDTO;
import com.example.demo.dto.response.BodegaResponseDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.mapper.BodegaMapper;
import com.example.demo.mapper.EmpleadoMapper;
import com.example.demo.model.Bodega;
import com.example.demo.model.Empleado;
import com.example.demo.repository.BodegaRepository;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.service.BodegaService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor

public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;
    private final BodegaMapper bodegaMapper;

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    @Override
    public BodegaResponseDTO crear(@NonNull BodegaRequestDTO dto) {
        Empleado e = empleadoRepository.findById(dto.idEncargado())
                .orElseThrow(() -> new BusinessRuleException("Error, no existe el empleado"));
        Bodega b = bodegaMapper.DTOAentidad(dto, e);
        Bodega bodega_insertada = bodegaRepository.save(b);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(e);
        return bodegaMapper.entidadADTO(bodega_insertada, dtoE);
    }

    @Override
    public List<BodegaResponseDTO> listar() {
        return bodegaRepository.findAll().stream()
                .map(dato -> {
                    Empleado e = dato.getIdEncargado();
                    return bodegaMapper.entidadADTO(dato, empleadoMapper.entidadADTO(e));
                })
                .toList();
    }

    @Override
    public BodegaResponseDTO buscarPorId(Long id) {
        Bodega b = bodegaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe la bodega"));

        Empleado e = b.getIdEncargado();
        return bodegaMapper.entidadADTO(b, empleadoMapper.entidadADTO(e));
    }

    @Override
    public BodegaResponseDTO actualizar(Long id, @NonNull BodegaRequestDTO dto) {
        Bodega b = bodegaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Error, no existe la bodega"));
        Empleado e = empleadoRepository.findById(dto.idEncargado())
                .orElseThrow(() -> new BusinessRuleException("Error, no existe el empleado"));
        bodegaMapper.actualizarEntidadDesdeDTO(b, dto, e);
        Bodega bodega_actualizada = bodegaRepository.save(b);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(e);
        return bodegaMapper.entidadADTO(bodega_actualizada, dtoE);
    }

    @Override
    public void eliminar(Long id) {
        Bodega b = bodegaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe la bodega"));
        bodegaRepository.delete(b);
    }

}
