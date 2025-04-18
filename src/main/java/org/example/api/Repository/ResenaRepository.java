package org.example.api.Repository;

import org.example.api.Model.Resena;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResenaRepository extends MongoRepository<Resena, String> {
    List<Resena> findByUserId_Id(String id);

}
