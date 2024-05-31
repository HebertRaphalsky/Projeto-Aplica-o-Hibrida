package br.unisales.projetos.demo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.unisales.projetos.demo.models.domain.Projeto;

@Repository


public interface ProjetoRepository extends MongoRepository<Projeto, String> {

 List<Projeto> findAllById();


}
