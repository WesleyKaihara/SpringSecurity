package br.com.example.springsecurity.Controller;

import br.com.example.springsecurity.Model.CarrinhoCompras;
import br.com.example.springsecurity.Model.Comentario;
import br.com.example.springsecurity.Repository.CarrinhoComprasRepository;
import br.com.example.springsecurity.Repository.ProdutoRepository;
import br.com.example.springsecurity.Repository.UserRepository;
import br.com.example.springsecurity.error.ProductNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

@RestController
public class CarrinhoComprasController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CarrinhoComprasRepository carrinhoComprasRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/carrinhoCompras")
    public ResponseEntity<Iterable<CarrinhoCompras>> listar (){
        Iterable<CarrinhoCompras> carrinhoCompras= carrinhoComprasRepository.findAll();
        return new ResponseEntity<>(carrinhoCompras,HttpStatus.OK);

    }

    @PostMapping("/carrinhoCompras/{id_produto}")
    public ResponseEntity<CarrinhoCompras> adicionar (@RequestBody Integer quantidade, @PathVariable("id_produto") Long id_produto) throws Exception {
        long user_id = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();

        if(produtoRepository.findById(id_produto).isEmpty()){
            throw new ProductNotExistsException();
        }

        if(!carrinhoComprasRepository.findIdByUserStatus(user_id,"Aguardando Pagamento",id_produto).isEmpty()){
            throw new Exception();
        }

            CarrinhoCompras carrinhoCompras = new CarrinhoCompras(quantidade,user_id,id_produto);
            carrinhoComprasRepository.save(carrinhoCompras);
            return new ResponseEntity<>(carrinhoCompras, HttpStatus.OK);
    }



}
