package com.example.demo.mapper;

import com.example.demo.dto.request.MovimientosDetalleRequestDTO;
import com.example.demo.dto.response.MovimientosDetalleResponseDTO;
import com.example.demo.dto.response.MovimientosResponseDTO;
import com.example.demo.dto.response.ProductoResponseDTO;
import com.example.demo.model.Movimientos;
import com.example.demo.model.MovimientosDetalle;
import com.example.demo.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class MovimientosDetalleMapper {

    public MovimientosDetalleResponseDTO entidadADTO(MovimientosDetalle detalle, MovimientosResponseDTO dtoM, ProductoResponseDTO dtoP) {
        if (detalle == null || dtoM == null || dtoP == null) return null;

        return new MovimientosDetalleResponseDTO(
                detalle.getId(),
                dtoM,
                dtoP,
                detalle.getCantidad()
        );
    }

    public MovimientosDetalle DTOAentidad(MovimientosDetalleRequestDTO dto, Movimientos movimiento, Producto producto) {
        if (dto == null || movimiento == null || producto == null) return null;

        MovimientosDetalle d = new MovimientosDetalle();
        d.setIdMovimiento(movimiento);
        d.setIdProducto(producto);
        d.setCantidad(dto.cantidad());

        return d;
    }

    public void actualizarEntidadDesdeDTO(MovimientosDetalle detalle, MovimientosDetalleRequestDTO dto, Movimientos movimiento, Producto producto) {
        if (detalle == null || dto == null || movimiento == null || producto == null) return;

        detalle.setIdMovimiento(movimiento);
        detalle.setIdProducto(producto);
        detalle.setCantidad(dto.cantidad());
    }

}
