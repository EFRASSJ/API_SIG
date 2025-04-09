package org.example.api.Service;

import org.example.api.Model.Categoria;
import org.example.api.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
