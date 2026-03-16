package com.example.demo.mapper;

import com.example.demo.dto.request.EmpleadoRequestDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;
import com.example.demo.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public EmpleadoResponseDTO entidadADTO(Empleado empleado) {
        if (empleado == null) return null;

        return new EmpleadoResponseDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getDocumento(),
                empleado.getCorreo(),
                empleado.getTelefono(),
                empleado.getRol()
        );
    }

    public Empleado DTOAentidad(EmpleadoRequestDTO dto) {
        if (dto == null) return null;

        Empleado e = new Empleado();
        e.setNombre(dto.nombre());
        e.setDocumento(dto.documento());
        e.setCorreo(dto.correo());
        e.setTelefono(dto.telefono());
        e.setRol(dto.rol());
        e.setUsuario(dto.usuario());
        e.setContrasena(dto.contrasena());

        return e;
    }

    public void actualizarEntidadDesdeDTO(Empleado empleado, EmpleadoRequestDTO dto) {
        if (empleado == null || dto == null) return;

        empleado.setNombre(dto.nombre());
        empleado.setDocumento(dto.documento());
        empleado.setCorreo(dto.correo());
        empleado.setTelefono(dto.telefono());

        empleado.setRol(dto.rol());
        empleado.setUsuario(dto.usuario());
        empleado.setContrasena(dto.contrasena());
    }

}
