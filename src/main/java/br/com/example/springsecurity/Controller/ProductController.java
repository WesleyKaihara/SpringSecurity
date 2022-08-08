package br.com.example.springsecurity.Controller;

import br.com.example.springsecurity.Model.Product;
import br.com.example.springsecurity.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Product productBody){
        Product product = new Product(productBody.getNome(),productBody.getValor(),productBody.getDescricao());
        if(productRepository.findByNome(productBody.getNome()) == null){
            productRepository.save(product);
            return new ResponseEntity<>("Produto cadastrado com sucesso",HttpStatus.OK);
        }
        return new ResponseEntity<>("Já existe um produto com este nome",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cadastrarProdutos");
        return modelAndView;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAll (){
        Iterable<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable long id,@RequestBody Product product){
        return productRepository.findById(id)
                .map(Pd -> {
                    Pd.setNome(product.getNome());
                    Pd.setDescricao(product.getDescricao());
                    Pd.setValor(product.getValor());
                    Product productUpdated = productRepository.save(Pd);
                    return ResponseEntity.ok().body(productUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
