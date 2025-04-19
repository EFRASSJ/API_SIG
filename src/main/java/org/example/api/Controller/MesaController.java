package org.example.api.Controller;


import org.example.api.Model.Mesa;
import org.example.api.Service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesa")
public class MesaController {
    @Autowired
    private MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    // Crear una nueva mesa
    @PostMapping
    public ResponseEntity<Mesa> saveMesa(@RequestBody Mesa mesa) {
        Mesa newMesa = mesaService.save(mesa);
        return newMesa == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(newMesa);
    }

    // Ver todas las mesas
    @GetMapping
    public ResponseEntity<List<Mesa>> getMesas() {
        List<Mesa> mesas = mesaService.getMesas();
        return mesas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(mesas);
    }

    // Buscar una mesa por nombre
    @GetMapping("/{nombre}")
    public ResponseEntity<Mesa> getMesaByNombre(@PathVariable String mesa) {
        Mesa newMesa = mesaService.getMesaByNombre(mesa);
        return newMesa == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(newMesa);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Mesa> updateMesa(@PathVariable String id, @RequestBody Mesa mesa) {
        Mesa updated = mesaService.updateMesa(id, mesa);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }
}
