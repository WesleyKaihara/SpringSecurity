package br.com.example.springsecurity.Repository;

import br.com.example.springsecurity.Model.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByRole(String role);
}

//cria referencia para criação de Roles da aplicação