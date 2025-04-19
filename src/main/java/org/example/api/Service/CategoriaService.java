package org.example.api.Service;

import org.example.api.Model.Categoria;
import org.example.api.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository CategoriaRepository;

    public CategoriaService(CategoriaRepository CategoriaRepository) {
        this.CategoriaRepository = CategoriaRepository;
    }

    // Crear una nueva categoría
    public Categoria save(Categoria categoria) {
        return CategoriaRepository.save(categoria);
    }

    // Ver todas las categorías
    public List<Categoria> getCategorias() {
        return CategoriaRepository.findAll();
    }

    // Buscar una categoría por nombre
    public Categoria getCategoriaByNombre(String nombre) {
        return CategoriaRepository.findAll()
                .stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
    public Categoria updateCategoria(String id, Categoria nuevaCategoria) {
        Optional<Categoria> optional = CategoriaRepository.findById(id);
        if (optional.isPresent()) {
            Categoria existente = optional.get();
            existente.setNombre(nuevaCategoria.getNombre());
            existente.setEstado(nuevaCategoria.getEstado());
            existente.setImagen(nuevaCategoria.getImagen());
            existente.setAdminId(nuevaCategoria.getAdminId());
            return CategoriaRepository.save(existente);
        }
        return null;
    }

}
