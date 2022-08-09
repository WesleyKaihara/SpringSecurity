package br.com.example.springsecurity.Repository;

import br.com.example.springsecurity.Model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto,Long> {
    Produto findByNome(String nome);

}
