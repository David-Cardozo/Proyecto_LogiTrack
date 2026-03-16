package com.example.demo.service.impl;

import com.example.demo.dto.request.InventarioRequestDTO;
import com.example.demo.dto.response.BodegaResponseDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;
import com.example.demo.dto.response.InventarioResponseDTO;
import com.example.demo.dto.response.ProductoResponseDTO;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.mapper.BodegaMapper;
import com.example.demo.mapper.EmpleadoMapper;
import com.example.demo.mapper.InventarioMapper;
import com.example.demo.mapper.ProductoMapper;
import com.example.demo.model.Bodega;
import com.example.demo.model.Inventario;
import com.example.demo.model.Producto;
import com.example.demo.repository.BodegaRepository;
import com.example.demo.repository.InventarioRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final InventarioMapper inventarioMapper;

    private final BodegaRepository bodegaRepository;
    private final BodegaMapper bodegaMapper;

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    private final EmpleadoMapper empleadoMapper;

    @Override
    public InventarioResponseDTO crear(@NonNull InventarioRequestDTO dto) {

        if (inventarioRepository.existsByIdBodega_IdAndIdProducto_Id(dto.idBodega(), dto.idProducto())) {
            throw new BusinessRuleException("Este producto ya existe");
        }

        Bodega b = bodegaRepository.findById(dto.idBodega())
                .orElseThrow(() -> new BusinessRuleException("No existe la bodega"));

        Producto p = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new BusinessRuleException("No existe el producto"));

        Inventario inv = inventarioMapper.DTOAentidad(dto, b, p);
        Inventario inv_insertado = inventarioRepository.save(inv);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(b.getIdEncargado());
        BodegaResponseDTO dtoB = bodegaMapper.entidadADTO(b, dtoE);
        ProductoResponseDTO dtoP = productoMapper.entidadADTO(p);

        return inventarioMapper.entidadADTO(inv_insertado, dtoB, dtoP);
    }

    @Override
    public List<InventarioResponseDTO> listar() {

        return inventarioRepository.findAll().stream()
                .map(dato -> {

                    Bodega b = dato.getIdBodega();
                    Producto p = dato.getIdProducto();
                    return inventarioMapper.entidadADTO(dato, bodegaMapper.entidadADTO(b, empleadoMapper.entidadADTO(b.getIdEncargado())), productoMapper.entidadADTO(p));
                })
                .toList();
    }

    @Override
    public InventarioResponseDTO buscarPorId(Long id) {

        Inventario inv = inventarioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe el inventario"));

        Bodega b = inv.getIdBodega();
        Producto p = inv.getIdProducto();
        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(b.getIdEncargado());

        return inventarioMapper.entidadADTO(inv, bodegaMapper.entidadADTO(b, dtoE), productoMapper.entidadADTO(p));
    }

    @Override
    public InventarioResponseDTO actualizar(Long id, @NonNull InventarioRequestDTO dto) {

        Inventario inv = inventarioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe el inventario a actualizar"));

        Bodega b = bodegaRepository.findById(dto.idBodega())
                .orElseThrow(() -> new BusinessRuleException("No existe la bodega"));

        Producto p = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new BusinessRuleException("No existe el producto"));

        inventarioMapper.actualizarEntidadDesdeDTO(inv, dto, b, p);

        Inventario inv_actualizado = inventarioRepository.save(inv);

        EmpleadoResponseDTO dtoE = empleadoMapper.entidadADTO(b.getIdEncargado());
        BodegaResponseDTO dtoB = bodegaMapper.entidadADTO(b, dtoE);
        ProductoResponseDTO dtoP = productoMapper.entidadADTO(p);

        return inventarioMapper.entidadADTO(inv_actualizado, dtoB, dtoP);
    }

    @Override
    public void eliminar(Long id) {

        Inventario inv = inventarioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("No existe el inventario a eliminar"));

        inventarioRepository.delete(inv);
    }

}

