package org.example.api.Controller;

import org.example.api.Model.Producto;
import org.example.api.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
        Producto newProducto = productoService.save(producto);
        return newProducto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(newProducto);
    }

    // Ver todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        List<Producto> productos = productoService.getProductos();
        return productos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productos);
    }

    // Buscar un producto por nombre
    @GetMapping("/{nombre}")
    public ResponseEntity<Producto> getProductoByNombre(@PathVariable String nombre) {
        Producto producto = productoService.getProductoByNombre(nombre);
        return producto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(producto);
    }
}
