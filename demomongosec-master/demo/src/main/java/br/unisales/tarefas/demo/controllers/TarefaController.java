package br.unisales.project.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisales.project.demo.annotations.ValidaAcesso;
import br.unisales.project.demo.models.domain.Projeto;
import br.unisales.project.demo.repositories.TarefaRepository;

@CrossOrigin
@RestController
@RequestMapping("/project")
@ValidaAcesso("ROLE_PROJETOS")
public class TarefaController extends DefaultController{
 @Autowired
 private TarefaRepository tarefaRepository;

 @PostMapping
 @ValidaAcesso({ "ROLE_USER" })
 public ResponseEntity<?> insere(@RequestBody Projeto projeto) {
  if (projeto != null) {
   projeto = tarefaRepository.save(projeto);
   return new ResponseEntity<>(projeto, HttpStatus.OK);
  }
  return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
 }

 @GetMapping
 public ResponseEntity<?> todas() {
  List<Projeto> lt = tarefaRepository.findAll();
  return new ResponseEntity<>(lt, HttpStatus.OK);
 }

}
