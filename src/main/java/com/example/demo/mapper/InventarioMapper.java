package com.example.demo.mapper;

import com.example.demo.dto.request.InventarioRequestDTO;
import com.example.demo.dto.response.BodegaResponseDTO;
import com.example.demo.dto.response.InventarioResponseDTO;
import com.example.demo.dto.response.ProductoResponseDTO;
import com.example.demo.model.Bodega;
import com.example.demo.model.Inventario;
import com.example.demo.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public InventarioResponseDTO entidadADTO(Inventario inventario, BodegaResponseDTO dtoB, ProductoResponseDTO dtoP) {
        if (inventario == null || dtoB == null || dtoP == null) return null;

        return new InventarioResponseDTO(
                inventario.getId(),
                dtoB,
                dtoP,
                inventario.getCantidad()
        );
    }

    public Inventario DTOAentidad(InventarioRequestDTO dto, Bodega bodega, Producto producto) {
        if (dto == null || bodega == null || producto == null) return null;

        Inventario i = new Inventario();
        i.setIdBodega(bodega);
        i.setIdProducto(producto);
        i.setCantidad(dto.cantidad());

        return i;
    }

    public void actualizarEntidadDesdeDTO(Inventario inventario, InventarioRequestDTO dto, Bodega bodega, Producto producto) {
        if (inventario == null || dto == null || bodega == null || producto == null) return;

        inventario.setIdBodega(bodega);
        inventario.setIdProducto(producto);
        inventario.setCantidad(dto.cantidad());
    }
}
