package org.example.api.Service;

import org.example.api.Model.Categoria;
import org.example.api.Model.Producto;
import org.example.api.Model.User;
import org.example.api.Repository.CategoriaRepository;
import org.example.api.Repository.ProductoRepository;
import org.example.api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository ProductoRepository;

    public ProductoService(ProductoRepository ProductoRepository) {
        this.ProductoRepository = ProductoRepository;
    }

    // Crear un nuevo producto
    public Producto save(Producto producto) {
        Producto nuevo = new Producto();

        nuevo.setNombre(producto.getNombre());
        nuevo.setPrecio(producto.getPrecio());
        nuevo.setDescripcion(producto.getDescripcion());
        nuevo.setImagen(producto.getImagen());

        if (producto.getAdminId() != null && producto.getAdminId().getId() != null) {
            Optional<User> userOptional = userRepository.findById(producto.getAdminId().getId());
            userOptional.ifPresent(nuevo::setAdminId);
        }

        if (producto.getCategorias() != null && !producto.getCategorias().isEmpty()) {
            List<String> categoriaIds = producto.getCategorias().stream()
                    .map(c -> c.getId()) // extraemos el id
                    .toList();

            List<Categoria> categorias = categoriaRepository.findAllById(categoriaIds);
            nuevo.setCategorias(categorias);
        }

        return ProductoRepository.save(nuevo);
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
