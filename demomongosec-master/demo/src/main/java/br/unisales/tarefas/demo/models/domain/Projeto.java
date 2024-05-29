package br.unisales.tarefas.demo.models.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("project")
public class Projeto {
 @Id
 private String id;
 private String descricao;
 private String professorRepresentante;
 
}
