package org.example.api.Service;

import org.example.api.Model.Mesa;
import org.example.api.Model.Resena;
import org.example.api.Repository.MesaRepository;
import org.example.api.Repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    public ResenaService(ResenaRepository resenaRepository, MesaRepository mesaRepository) {
        this.resenaRepository = resenaRepository;
        this.mesaRepository = mesaRepository;
    }

    // Crear una nueva reseña
    public Resena save(Resena resena) {
        // Buscar la mesa por su nombre si viene sin el objeto mesa completo
        if (resena.getMesaId() != null && resena.getMesaId().getMesa() != null) {
            Mesa mesaEncontrada = mesaRepository.findByMesa(resena.getMesaId().getMesa());
            if (mesaEncontrada != null) {
                resena.setMesaId(mesaEncontrada);
            } else {
                throw new RuntimeException("Mesa no encontrada con el nombre: " + resena.getMesaId().getMesa());
            }
        }
        return resenaRepository.save(resena);
    }

    // Obtener todas las reseñas
    public List<Resena> getResenas() {
        return resenaRepository.findAll();
    }

    // Buscar reseñas por ID del empleado (mesero)
//    public List<Resena> getResenasByEmpleadoId(String id) {
//        return resenaRepository.findByUserId_Id(id);
//    }
}
