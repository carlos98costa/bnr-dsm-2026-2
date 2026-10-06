package br.edu.fatecfranca.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatecfranca.api.models.Modulo;

public interface ModuloRepository extends MongoRepository<Modulo, String> {
}
