package org.example.api.Service;

import org.example.api.Model.Resena;
import org.example.api.Repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;

    public ResenaService(ResenaRepository resenaRepository) {
        this.resenaRepository = resenaRepository;
    }

    // Crear una nueva reseña
    public Resena save(Resena resena) {
        return resenaRepository.save(resena);
    }

    // Obtener todas las reseñas
    public List<Resena> getResenas() {
        return resenaRepository.findAll();
    }

    // Buscar reseñas por ID del empleado (mesero)
    public List<Resena> getResenasByEmpleadoId(String empleadoId) {
        return resenaRepository.findByEmpleadoId(empleadoId);
    }
}
