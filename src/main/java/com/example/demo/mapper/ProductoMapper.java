package com.example.demo.mapper;

import com.example.demo.dto.request.ProductoRequestDTO;
import com.example.demo.dto.response.ProductoResponseDTO;
import com.example.demo.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoResponseDTO entidadADTO(Producto producto) {
        if (producto == null) return null;

        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getTamano(),
                producto.getPrecioMensual()
        );
    }

    public Producto DTOAentidad(ProductoRequestDTO dto) {
        if (dto == null) return null;

        Producto p = new Producto();
        p.setNombre(dto.nombre());
        p.setCategoria(dto.categoria());
        p.setTamano(dto.tamano());
        p.setPrecioMensual(dto.precioMensual());

        return p;
    }

    public void actualizarEntidadDesdeDTO(Producto producto, ProductoRequestDTO dto) {
        if (producto == null || dto == null) return;

        producto.setNombre(dto.nombre());
        producto.setCategoria(dto.categoria());
        producto.setTamano(dto.tamano());
        producto.setPrecioMensual(dto.precioMensual());
    }

}
