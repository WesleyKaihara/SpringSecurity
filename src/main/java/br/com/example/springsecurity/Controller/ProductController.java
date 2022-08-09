package br.com.example.springsecurity.Controller;

import br.com.example.springsecurity.Model.Product;
import br.com.example.springsecurity.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ProductController {

    private static String PathImages = "/Users/Administrador/Documents/Wesley/SpringBoot/imagens/";
    @Autowired
    ProductRepository productRepository;

    @PostMapping(value  = "/product",consumes = "multipart/form-data")
    public ResponseEntity<String> save(Product productBody,@RequestParam("file") MultipartFile arquivo){
        if(productRepository.findByNome(productBody.getNome()) == null){
            productRepository.save(productBody);
        }else{
            return new ResponseEntity<>("Já existe um produto com este nome",HttpStatus.BAD_REQUEST);
        }

        try{
            if(!arquivo.isEmpty()){
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(PathImages+String.valueOf(productBody.getId())+arquivo.getOriginalFilename());
                Files.write(caminho,bytes);
                productBody.setNomeImagem(String.valueOf(productBody.getId())+arquivo.getOriginalFilename());
                productRepository.save(productBody);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Produto cadastrado com sucesso",HttpStatus.OK);
    }


    @GetMapping("/product")
    public ResponseEntity<Iterable<Product>> getAll () {
        Iterable<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
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

    @GetMapping("/product/img/{imagem}")
    @ResponseBody
    public byte[] getImage (@PathVariable("imagem") String imagem) throws IOException {
        File imagemArquivo = new File(PathImages+imagem);
        return Files.readAllBytes(imagemArquivo.toPath());
    }
}