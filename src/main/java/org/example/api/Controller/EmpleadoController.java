package org.example.api.Controller;


import org.example.api.Model.Empleado;
import org.example.api.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<Empleado> saveEmpleado(@RequestBody Empleado empleado) {
        Empleado newEmpleado = empleadoService.save(empleado);
        return newEmpleado == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(newEmpleado);
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados() {
        List<Empleado> empleados = empleadoService.getEmpleados();
        return empleados.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(empleados);
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Empleado> getEmpleadoByNombre(@PathVariable String nombre) {
        Empleado empleado = empleadoService.getEmpleadoByNombre(nombre);
        return empleado == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(empleado);
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable String nombre, @RequestBody Empleado empleado) {
        Empleado empleadoUpdated = empleadoService.updateEmpleado(nombre, empleado);
        return empleadoUpdated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(empleadoUpdated);
    }
}
