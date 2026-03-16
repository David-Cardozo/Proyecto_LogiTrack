package com.example.demo.mapper;

import com.example.demo.dto.request.BodegaRequestDTO;
import com.example.demo.dto.response.BodegaResponseDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;
import com.example.demo.model.Bodega;
import com.example.demo.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class BodegaMapper {

    public BodegaResponseDTO entidadADTO(Bodega bodega, EmpleadoResponseDTO dtoE) {
        if (bodega == null) return null;

        return new BodegaResponseDTO(
                bodega.getId(),
                bodega.getNombre(),
                bodega.getUbicacion(),
                bodega.getCapacidad(),
                dtoE
        );
    }

    public Bodega DTOAentidad(BodegaRequestDTO dto, Empleado empleado) {
        if (dto == null || empleado == null) return null;

        Bodega b = new Bodega();
        b.setNombre(dto.nombre());
        b.setUbicacion(dto.ubicacion());
        b.setCapacidad(dto.capacidad());
        b.setIdEncargado(empleado);

        return b;
    }

    public void actualizarEntidadDesdeDTO(Bodega bodega, BodegaRequestDTO dto, Empleado empleado) {
        if (bodega == null || dto == null || empleado == null) return;

        bodega.setNombre(dto.nombre());
        bodega.setUbicacion(dto.ubicacion());
        bodega.setCapacidad(dto.capacidad());
        bodega.setIdEncargado(empleado);
    }
}

