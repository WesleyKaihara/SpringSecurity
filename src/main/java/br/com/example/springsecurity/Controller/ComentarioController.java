package br.com.example.springsecurity.Controller;

import br.com.example.springsecurity.Model.Comentario;
import br.com.example.springsecurity.Model.Produto;
import br.com.example.springsecurity.Repository.ComentarioRepository;
import br.com.example.springsecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComentarioController {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/comentario")
    public ResponseEntity<Comentario> Criar(@RequestBody String content){
        long user_id = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        Comentario comentario = new Comentario(user_id,content);
        comentarioRepository.save(comentario);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    @GetMapping("/comentarios")
    public ResponseEntity<Iterable<Comentario>> Listar(){
        Iterable<Comentario> comentarios = comentarioRepository.findAll();
        return new ResponseEntity<>(comentarios,HttpStatus.OK);
    }

    @PutMapping("/comentario/{id}")
    public ResponseEntity<Comentario> atualizar(@PathVariable long id,@RequestBody String comentarioBody){
        return comentarioRepository.findById(id)
                .map(comentario ->{
                            comentario.setContent(comentarioBody);
                            Comentario comentarioUpdated = comentarioRepository.save(comentario);
                            return ResponseEntity.ok().body(comentarioUpdated);
                        }).orElse(ResponseEntity.notFound().build());
    }

}
