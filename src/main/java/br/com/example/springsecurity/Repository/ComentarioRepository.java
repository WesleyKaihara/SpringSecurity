package br.com.example.springsecurity.Repository;

import br.com.example.springsecurity.Model.Comentario;
import org.springframework.data.repository.CrudRepository;

public interface ComentarioRepository extends CrudRepository<Comentario,Long> {
}
