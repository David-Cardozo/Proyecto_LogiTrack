package com.example.demo.service.impl;

import com.example.demo.dto.request.MovimientosDetalleRequestDTO;
import com.example.demo.dto.response.BodegaResponseDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;
import com.example.demo.dto.response.MovimientosDetalleResponseDTO;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.MovimientosDetalleService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientosDetalleServiceImpl implements MovimientosDetalleService {

    private final MovimientosDetalleRepository detalleMovimientoRepository;
    private final MovimientosDetalleMapper detalleMovimientoMapper;

    private final MovimientosRepository movimientoRepository;
    private final MovimientosMapper movimientoMapper;

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    private final EmpleadoMapper empleadoMapper;
    private final BodegaMapper bodegaMapper;

    private final InventarioRepository inventarioRepository;
    private final BodegaRepository bodegaRepository;

    @Override
    public MovimientosDetalleResponseDTO crear(@NonNull MovimientosDetalleRequestDTO dto) {

        Movimientos m = movimientoRepository.findById(dto.idMovimiento())
                .orElseThrow(() -> new BusinessRuleException("Movimiento Inexistente"));

        Producto p = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new BusinessRuleException("Producto Inexistente"));

        Bodega origen = m.getIdBodegaOrigen();
        Bodega destino = m.getIdBodegaDestino();

        Inventario invOrigen = inventarioRepository
                .findByIdBodega_IdAndIdProducto_Id(origen.getId(), p.getId());

        if (invOrigen == null) {
            throw new BusinessRuleException("Producto no encontrado en la bodega consultada");
        }
        if (invOrigen.getCantidad() < dto.cantidad()) {
            throw new BusinessRuleException("No hay suficientes unidades " +
                    "Stock actual: " + invOrigen.getCantidad());
        }

        if (destino.getCapacidad() < dto.cantidad()) {
            throw new BusinessRuleException("No hay espacio en al bodega. " +
                    "Espacio disponible: " + destino.getCapacidad());
        }

        invOrigen.setCantidad(invOrigen.getCantidad() - dto.cantidad());
        inventarioRepository.save(invOrigen);

        origen.setCapacidad(origen.getCapacidad() + dto.cantidad());
        bodegaRepository.save(origen);

        destino.setCapacidad(destino.getCapacidad() - dto.cantidad());
        bodegaRepository.save(destino);

        Inventario invDestino = inventarioRepository
                .findByIdBodega_IdAndIdProducto_Id(destino.getId(), p.getId());

        if (invDestino != null) {
            invDestino.setCantidad(invDestino.getCantidad() + dto.cantidad());
            inventarioRepository.save(invDestino);
        } else {
            Inventario nuevo = new Inventario();
            nuevo.setIdBodega(destino);
            nuevo.setIdProducto(p);
            nuevo.setCantidad(dto.cantidad());
            inventarioRepository.save(nuevo);
        }

        MovimientosDetalle d = detalleMovimientoMapper.DTOAentidad(dto, m, p);
        MovimientosDetalle dInsertado = detalleMovimientoRepository.save(d);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(m.getIdEmpleado());
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(origen, dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(destino, dtoE);

        return detalleMovimientoMapper.entidadADTO(
                dInsertado,
                movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD),
                productoMapper.entidadADTO(p)
        );
    }

    @Override
    public MovimientosDetalleResponseDTO buscarPorId(Long id) {

        MovimientosDetalle d = detalleMovimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe el detalle se ese movimiento"));

        Movimientos m = d.getIdMovimiento();
        Producto p = d.getIdProducto();

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(m.getIdEmpleado());
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(m.getIdBodegaOrigen(), dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(m.getIdBodegaDestino(), dtoE);

        return detalleMovimientoMapper.entidadADTO(
                d,
                movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD),
                productoMapper.entidadADTO(p)
        );
    }

    @Override
    public MovimientosDetalleResponseDTO actualizar(Long id, @NonNull MovimientosDetalleRequestDTO dto) {

        MovimientosDetalle d = detalleMovimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe registro del movimiento"));

        Movimientos m = movimientoRepository.findById(dto.idMovimiento())
                .orElseThrow(() -> new BusinessRuleException("Este movimiento Inexistente"));

        Producto p = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new BusinessRuleException("Producto Inexistente"));

        detalleMovimientoMapper.actualizarEntidadDesdeDTO(d, dto, m, p);
        MovimientosDetalle actualizado = detalleMovimientoRepository.save(d);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(m.getIdEmpleado());
        BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(m.getIdBodegaOrigen(), dtoE);
        BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(m.getIdBodegaDestino(), dtoE);

        return detalleMovimientoMapper.entidadADTO(
                actualizado,
                movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD),
                productoMapper.entidadADTO(p)
        );
    }

    @Override
    public void eliminar(Long id) {

        MovimientosDetalle d = detalleMovimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Detalle de movimiento Inexistente"));

        detalleMovimientoRepository.delete(d);
    }

    @Override
    public List<MovimientosDetalleResponseDTO> buscarPorMovimientoId(Long idMovimiento) {

        return detalleMovimientoRepository.findByIdMovimientoId(idMovimiento).stream()
                .map(dato -> {

                    Movimientos m = dato.getIdMovimiento();
                    Producto p = dato.getIdProducto();

                    EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(m.getIdEmpleado());
                    BodegaResponseDTO dtoBO = bodegaMapper.entidadADTO(m.getIdBodegaOrigen(), dtoE);
                    BodegaResponseDTO dtoBD = bodegaMapper.entidadADTO(m.getIdBodegaDestino(), dtoE);

                    return detalleMovimientoMapper.entidadADTO(
                            dato,
                            movimientoMapper.entidadADTO(m, dtoE, dtoBO, dtoBD),
                            productoMapper.entidadADTO(p)
                    );
                })
                .toList();
    }
}
