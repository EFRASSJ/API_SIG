package org.example.api.Service;

import org.example.api.Model.Orden;
import org.example.api.Repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository OrdenRepository;

    public OrdenService(OrdenRepository OrdenRepository) {
        this.OrdenRepository = OrdenRepository;
    }

    // Crear una nueva orden
    public Orden save(Orden orden) {
        return OrdenRepository.save(orden);
    }

    // Obtener todas las órdenes
    public List<Orden> getOrdenes() {
        return OrdenRepository.findAll();
    }

    // Buscar una orden por ID de mesa
    public List<Orden> getOrdenesByMesaId(String mesaId) {
        return OrdenRepository.findAll()
                .stream()
                .filter(o -> o.getMesaId().equals(mesaId))
                .toList();
    }

    // Editar una orden
    public Orden updateOrden(String id, Orden orden) {
        Orden ordenToUpdate = OrdenRepository.findById(id).orElse(null);
        if (ordenToUpdate == null) {
            return null;
        }
        if (orden.getFecha() != null) {
            ordenToUpdate.setFecha(orden.getFecha());
        }
        if (orden.getEstado() != null) {
            ordenToUpdate.setEstado(orden.getEstado());
        }
        if (orden.getComentario() != null) {
            ordenToUpdate.setComentario(orden.getComentario());
        }
        if (orden.getMesaId() != null) {
            ordenToUpdate.setMesaId(orden.getMesaId());
        }
        if (orden.getProductos() != null) {
            ordenToUpdate.setProductos(orden.getProductos());
        }
        return OrdenRepository.save(ordenToUpdate);
    }
}
