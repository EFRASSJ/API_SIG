package org.example.api.Repository;

import org.example.api.Model.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrdenRepository extends MongoRepository<Orden, String> {
    List<Orden> findByMesaId(String mesaId);

}
