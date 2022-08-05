package br.com.example.springsecurity.Repository;

import br.com.example.springsecurity.Model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long>{
    User findByUsername(String username);
}

//referencia para criaçaõ de usuário na aplicação