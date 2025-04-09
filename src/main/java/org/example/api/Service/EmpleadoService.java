package org.example.api.Service;

import org.example.api.Model.Empleado;
import org.example.api.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository EmpleadoRepository;

    public EmpleadoService(EmpleadoRepository EmpleadoRepository) {
        this.EmpleadoRepository = EmpleadoRepository;
    }

    // Añadir nuevo empleado
    public Empleado save(Empleado empleado) {
        return EmpleadoRepository.save(empleado);
    }

    // Ver todos los empleados
    public List<Empleado> getEmpleados() {
        return EmpleadoRepository.findAll();
    }

    // Ver empleado por el nombre
    public Empleado getEmpleadoByNombre(String nombre) {
        return EmpleadoRepository.findAll()
                .stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    // Editar un empleado
    public Empleado updateEmpleado(String nombre, Empleado empleado) {
        Empleado empleadoToUpdate = getEmpleadoByNombre(nombre);
        if (empleadoToUpdate == null) {
            return null;
        }
        if (empleado.getNombre() != null) {
            empleadoToUpdate.setNombre(empleado.getNombre());
        }
        if (empleado.getCorreo() != null) {
            empleadoToUpdate.setCorreo(empleado.getCorreo());
        }
        if (empleado.getContrasena() != null) {
            empleadoToUpdate.setContrasena(empleado.getContrasena());
        }
        if (empleado.getRol() != null) {
            empleadoToUpdate.setRol(empleado.getRol());
        }
        return EmpleadoRepository.save(empleadoToUpdate);
    }
}
