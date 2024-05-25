package br.unisales.projetos.demo.controllers;

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

import br.unisales.projetos.demo.annotations.ValidaAcesso;
import br.unisales.projetos.demo.models.domain.Projeto;
import br.unisales.projetos.demo.repositories.ProjetoRepository;

@CrossOrigin
@RestController
@RequestMapping("/projetos")
@ValidaAcesso("ROLE_PROJETOS")
public class ProjetoController extends DefaultController{
 @Autowired
 private ProjetoRepository projetosRepository;

 @PostMapping
 @ValidaAcesso({ "ROLE_USER" })
 public ResponseEntity<?> insere(@RequestBody Projeto projetos) {
  if (projetos != null) {
   projetos = projetosRepository.save(projetos);
   return new ResponseEntity<>(projetos, HttpStatus.OK);
  }
  return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
 }

 @GetMapping
 public ResponseEntity<?> todas() {
  List<Projeto> lt = projetosRepository.findAll();
  return new ResponseEntity<>(lt, HttpStatus.OK);
 }

}
