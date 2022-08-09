package br.com.example.springsecurity.Repository;

import br.com.example.springsecurity.Model.Filamento;
import org.springframework.data.repository.CrudRepository;

public interface FilamentosRepository extends CrudRepository<Filamento,Long> {
    Filamento findByCor(String cor);
}
