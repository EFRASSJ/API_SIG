package org.example.api.Controller;


import org.example.api.Model.Resena;
import org.example.api.Service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resena")
public class ResenaController {
    @Autowired
    private ResenaService resenaService;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    // Crear una nueva reseña
    @PostMapping
    public ResponseEntity<Resena> saveResena(@RequestBody Resena resena) {
        Resena newResena = resenaService.save(resena);
        return newResena == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(newResena);
    }

    // Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<Resena>> getResenas() {
        List<Resena> resenas = resenaService.getResenas();
        return resenas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(resenas);
    }

    // Buscar reseñas por ID del empleado (mesero)
    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<Resena>> getResenasByEmpleadoId(@PathVariable String empleadoId) {
        List<Resena> resenas = resenaService.getResenasByEmpleadoId(empleadoId);
        return resenas.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(resenas);
    }
}
