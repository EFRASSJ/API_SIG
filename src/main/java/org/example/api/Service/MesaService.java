package org.example.api.Service;


import org.example.api.Model.Mesa;
import org.example.api.Repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {
    @Autowired
    private MesaRepository MesaRepository;

    public MesaService(MesaRepository MesaRepository) {
        this.MesaRepository = MesaRepository;
    }

    // Crear una nueva mesa
    public Mesa save(Mesa mesa) {
        return MesaRepository.save(mesa);
    }

    // Ver todas las mesas
    public List<Mesa> getMesas() {
        return MesaRepository.findAll();
    }

    // Buscar una mesa por nombre
    public Mesa getMesaByNombre(String mesa) {
        return MesaRepository.findAll()
                .stream()
                .filter(m -> m.getMesa().equalsIgnoreCase(mesa))
                .findFirst()
                .orElse(null);
    }
    // Actualizar una mesa existente
    public Mesa updateMesa(String id, Mesa mesa) {
        Optional<Mesa> optionalMesa = MesaRepository.findById(id);
        if (optionalMesa.isPresent()) {
            Mesa existente = optionalMesa.get();

            // Actualizar campos según los datos recibidos
            existente.setMesa(mesa.getMesa());
            existente.setCapacidad(mesa.getCapacidad());
            existente.setImagen(mesa.getImagen());
            existente.setEstado(mesa.getEstado()); // Asegúrate de tener este campo

            return MesaRepository.save(existente);
        } else {
            return null;
        }
    }
}
