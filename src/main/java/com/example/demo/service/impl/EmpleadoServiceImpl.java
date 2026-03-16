package com.example.demo.service.impl;

import com.example.demo.dto.request.EmpleadoRequestDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.mapper.EmpleadoMapper;
import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    @Override
    public EmpleadoResponseDTO crear(EmpleadoRequestDTO dto) {
        Empleado e = empleadoMapper.DTOAentidad(dto);
        Empleado e_insertado = empleadoRepository.save(e);

        return empleadoMapper.entidadADTO(e_insertado);
    }

    @Override
    public List<EmpleadoResponseDTO> listar() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::entidadADTO)
                .toList();
    }

    @Override
    public EmpleadoResponseDTO buscarPorId(Long id) {
        Empleado e = empleadoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe dicho empleado"));
        return empleadoMapper.entidadADTO(e);
    }

    @Override
    public EmpleadoResponseDTO actualizar(Long id, EmpleadoRequestDTO dto) {
        Empleado e = empleadoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe dicho empleado"));
        empleadoMapper.actualizarEntidadDesdeDTO(e, dto);
        Empleado e_actualizado = empleadoRepository.save(e);
        return empleadoMapper.entidadADTO(e_actualizado);
    }

    @Override
    public void eliminar(Long id) {
        Empleado e = empleadoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe dicho empleado"));
        empleadoRepository.delete(e);
    }
}

