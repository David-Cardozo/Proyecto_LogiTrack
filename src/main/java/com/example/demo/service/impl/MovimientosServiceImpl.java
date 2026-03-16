package com.example.demo.service.impl;

import com.example.demo.dto.request.MovimientosDetalleRequestDTO;
import com.example.demo.dto.request.MovimientosRequestDTO;
import com.example.demo.dto.response.BodegaResponseDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;
import com.example.demo.dto.response.MovimientosResponseDTO;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.mapper.BodegaMapper;
import com.example.demo.mapper.EmpleadoMapper;
import com.example.demo.mapper.MovimientosMapper;
import com.example.demo.model.Bodega;
import com.example.demo.model.Empleado;
import com.example.demo.model.Movimientos;
import com.example.demo.repository.BodegaRepository;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.MovimientosRepository;
import com.example.demo.service.MovimientosService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientosServiceImpl implements MovimientosService {

    private final MovimientosRepository movimientoRepository;
    private final MovimientosMapper movimientoMapper;

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    private final BodegaRepository bodegaRepository;
    private final BodegaMapper bodegaMapper;

    @Override
    public MovimientosResponseDTO crear(@NonNull MovimientosRequestDTO dto) {

        Empleado e = empleadoRepository.findById(dto.idEmpleado())
                .orElseThrow(() -> new BusinessRuleException("Empleado Inexistente"));

        Bodega bo = bodegaRepository.findById(dto.idBodegaOrigen())
                .orElseThrow(() -> new BusinessRuleException("Bodega seleccionada Inexistente"));

        Bodega bd = bodegaRepository.findById(dto.idBodegaDestino())
                .orElseThrow(() -> new BusinessRuleException("Bodega destino Inexistente"));

        Movimientos m = movimientoMapper.DTOAentidad(dto, e, bo, bd);
        Movimientos m_insertado = movimientoRepository.save(m);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(e);
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(bo, dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(bd, dtoE);

        return movimientoMapper.entidadADTO(m_insertado, dtoE, dtoBO, dtoBD);
    }

    @Override
    public List<MovimientosResponseDTO> listar() {

        return movimientoRepository.findAll().stream()
                .map(dato -> {

                    Empleado e = dato.getIdEmpleado();
                    Bodega bo = dato.getIdBodegaOrigen();
                    Bodega bd = dato.getIdBodegaDestino();

                    return movimientoMapper.entidadADTO(dato, empleadoMapper.entidadADTO(e), bodegaMapper.entidadADTO(bo, empleadoMapper.entidadADTO(e)), bodegaMapper.entidadADTO(bd, empleadoMapper.entidadADTO(e)));
                })
                .toList();
    }

    @Override
    public MovimientosResponseDTO buscarPorId(Long id) {

        Movimientos m = movimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Movimiento Inexistente"));

        Empleado e = m.getIdEmpleado();
        Bodega bo = m.getIdBodegaOrigen();
        Bodega bd = m.getIdBodegaDestino();

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(e);
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(bo, dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(bd, dtoE);

        return movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD);
    }

    @Override
    public MovimientosResponseDTO actualizar(Long id, @NonNull MovimientosRequestDTO dto) {

        Movimientos m = movimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Movimiento Inexistente"));

        Empleado e = empleadoRepository.findById(dto.idEmpleado())
                .orElseThrow(() -> new BusinessRuleException("Empleado Inexsistente"));

        Bodega bo = bodegaRepository.findById(dto.idBodegaOrigen())
                .orElseThrow(() -> new BusinessRuleException("Bodega seleccionada Inexistente"));

        Bodega bd = bodegaRepository.findById(dto.idBodegaDestino())
                .orElseThrow(() -> new BusinessRuleException("Bodega destino Inexistente"));

        movimientoMapper.actualizarEntidadDesdeDTO(m, dto, e, bo, bd);

        Movimientos m_actualizado = movimientoRepository.save(m);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(e);
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(bo, dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(bd, dtoE);

        return movimientoMapper.entidadADTO(m_actualizado, dtoE, dtoBO, dtoBD);
    }

    @Override
    public void eliminar(Long id) {

        Movimientos m = movimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Movimiento Inexistente"));

        movimientoRepository.delete(m);
    }

}

