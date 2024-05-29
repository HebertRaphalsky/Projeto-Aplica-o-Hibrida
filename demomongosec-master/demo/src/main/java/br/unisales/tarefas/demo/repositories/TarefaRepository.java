package br.unisales.project.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.unisales.project.demo.models.domain.Projeto;

@Repository
public interface TarefaRepository extends MongoRepository<Projeto, String> {

}
