package org.example.api.Repository;

import org.example.api.Model.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpleadoRepository extends MongoRepository<Empleado, String> {
}
