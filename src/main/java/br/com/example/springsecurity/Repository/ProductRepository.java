package br.com.example.springsecurity.Repository;

import br.com.example.springsecurity.Model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
    Product findByNome(String nome);
}
