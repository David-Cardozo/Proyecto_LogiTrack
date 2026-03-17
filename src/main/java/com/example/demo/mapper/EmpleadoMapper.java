package com.example.demo.mapper;

import com.example.demo.dto.request.EmpleadoRequestDTO;
import com.example.demo.dto.response.EmpleadoResponseDTO;
import com.example.demo.model.Empleado;
import com.example.demo.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public EmpleadoResponseDTO entidadADTO(Empleado empleado) {
        if (empleado == null) return null;

        return new EmpleadoResponseDTO(
                empleado.getId(),
                empleado.getPersona().getNombre(),
                empleado.getPersona().getDocumento(),
                empleado.getPersona().getCorreo(),
                empleado.getPersona().getTelefono(),
                empleado.getRol()
        );
    }

    public Empleado DTOAentidad(EmpleadoRequestDTO dto) {
        if (dto == null) return null;

        Persona persona = new Persona();
        persona.setNombre(dto.nombre());
        persona.setDocumento(dto.documento());
        persona.setCorreo(dto.correo());
        persona.setTelefono(dto.telefono());

        Empleado e = new Empleado();
        e.setPersona(persona);
        e.setRol(dto.rol());
        e.setUsuario(dto.usuario());
        e.setContrasena(dto.contrasena());

        return e;
    }

    public void actualizarEntidadDesdeDTO(Empleado empleado, EmpleadoRequestDTO dto) {
        if (empleado == null || dto == null) return;

        Persona persona = empleado.getPersona();

        persona.setNombre(dto.nombre());
        persona.setDocumento(dto.documento());
        persona.setCorreo(dto.correo());
        persona.setTelefono(dto.telefono());

        empleado.setRol(dto.rol());
        empleado.setUsuario(dto.usuario());
        empleado.setContrasena(dto.contrasena());
    }
}