package org.example.api.Repository;

import org.example.api.Model.Mesa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MesaRepository extends MongoRepository<Mesa, String> {
}
