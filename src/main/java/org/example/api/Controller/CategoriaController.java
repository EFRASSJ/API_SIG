package org.example.api.Controller;

import org.example.api.Model.Categoria;
import org.example.api.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
        Categoria newCategoria = categoriaService.save(categoria);
        return newCategoria == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(newCategoria);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable String id, @RequestBody Categoria categoria) {
        Categoria updated = categoriaService.updateCategoria(id, categoria);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }


    // Ver todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> getCategorias() {
        List<Categoria> categorias = categoriaService.getCategorias();
        return categorias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categorias);
    }

    // Buscar una categoría por nombre
    @GetMapping("/{nombre}")
    public ResponseEntity<Categoria> getCategoriaByNombre(@PathVariable String nombre) {
        Categoria categoria = categoriaService.getCategoriaByNombre(nombre);
        return categoria == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(categoria);
    }
}

