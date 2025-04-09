package org.example.api.Service;

import org.example.api.Model.Producto;
import org.example.api.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository ProductoRepository;

    public ProductoService(ProductoRepository ProductoRepository) {
        this.ProductoRepository = ProductoRepository;
    }

    // Crear un nuevo producto
    public Producto save(Producto producto) {
        return ProductoRepository.save(producto);
    }

    // Ver todos los productos
    public List<Producto> getProductos() {
        return ProductoRepository.findAll();
    }

    // Buscar un producto por nombre
    public Producto getProductoByNombre(String nombre) {
        return ProductoRepository.findAll()
                .stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
}
