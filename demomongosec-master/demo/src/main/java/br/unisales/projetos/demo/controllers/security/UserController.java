package br.unisales.projetos.demo.controllers.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisales.projetos.demo.annotations.ValidaAcesso;
import br.unisales.projetos.demo.controllers.DefaultController;
import br.unisales.projetos.demo.models.security.User;
import br.unisales.projetos.demo.repositories.security.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/users")
@ValidaAcesso("ROLE_ADMIN")
public class UserController extends DefaultController {

 @Autowired
 UserRepository userRepository;

 @Autowired
 PasswordEncoder passwordEncoder;


 @SuppressWarnings({ "unchecked", "rawtypes" })
 @GetMapping("")
 public ResponseEntity<List<User>> todosPorLogin() {
  return new ResponseEntity(userRepository.findAllByOrderByLoginAsc(), HttpStatus.OK);
 }

 @PostMapping("")
public ResponseEntity<?> salva(@RequestBody User user) {
    user.setSenha(passwordEncoder.encode(user.getSenha()));

    // Adiciona a role ROLE_USER ao campo permissoes

    if (user.getPermissoes() == null) {
     user.setPermissoes(new ArrayList<>());
 }
 if (!user.getPermissoes().contains("ROLE_USER")) {
     user.getPermissoes().add("ROLE_USER");
 }

    if (user.getId() != null) {
        user = userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.resolve(204));
    } else {
        user = userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

 }

