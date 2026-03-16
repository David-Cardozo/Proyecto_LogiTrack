package com.example.demo.mapper;

import com.example.demo.dto.request.MovimientosRequestDTO;
import com.example.demo.dto.response.BodegaResponseDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;
import com.example.demo.dto.response.MovimientosResponseDTO;
import com.example.demo.model.Bodega;
import com.example.demo.model.Empleado;
import com.example.demo.model.Movimientos;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

@Component
public class MovimientosMapper {

    public MovimientosResponseDTO entidadADTO(Movimientos movimiento, EmpleadoResponseDTO dtoE, BodegaResponseDTO dtoBO, BodegaResponseDTO dtoBD) {
        if (movimiento == null || dtoE == null || dtoBO == null || dtoBD == null) return null;

        return new MovimientosResponseDTO(
                movimiento.getId(),
                movimiento.getFecha(),
                movimiento.getTipoMovimiento().toString(),
                dtoE,
                dtoBO,
                dtoBD
        );
    }

    public Movimientos DTOAentidad(MovimientosRequestDTO dto, Empleado empleado, Bodega bodegaOrigen, Bodega bodegaDestino) {
        if (dto == null || empleado == null || bodegaOrigen == null || bodegaDestino == null) return null;

        Movimientos m = new Movimientos();
        m.setFecha(dto.fecha());
        m.setTipoMovimiento(dto.tipoMovimiento());
        m.setIdEmpleado(empleado);
        m.setIdBodegaOrigen(bodegaOrigen);
        m.setIdBodegaDestino(bodegaDestino);

        return m;
    }

    public void actualizarEntidadDesdeDTO(Movimientos movimiento, MovimientosRequestDTO dto, Empleado empleado, Bodega bodegaOrigen, Bodega bodegaDestino) {
        if (movimiento == null || dto == null || empleado == null || bodegaOrigen == null || bodegaDestino == null)
            return;

        movimiento.setFecha(dto.fecha());
        movimiento.setTipoMovimiento(dto.tipoMovimiento());
        movimiento.setIdEmpleado(empleado);
        movimiento.setIdBodegaOrigen(bodegaOrigen);
        movimiento.setIdBodegaDestino(bodegaDestino);
    }

}
