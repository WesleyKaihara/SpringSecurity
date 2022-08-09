package br.com.example.springsecurity.Controller;

import br.com.example.springsecurity.Model.Produto;
import br.com.example.springsecurity.Repository.ProdutoRepository;
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
public class ProdutoController {

    private static String PathImages = "/Users/Administrador/Documents/Wesley/SpringBoot/imagens/";
    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping(value  = "/produto",consumes = "multipart/form-data")
    public ResponseEntity<String> save(Produto produtoBody,@RequestParam("file") MultipartFile arquivo){
        if(produtoRepository.findByNome(produtoBody.getNome()) == null){
            produtoRepository.save(produtoBody);
        }else{
            return new ResponseEntity<>("Já existe um produto com este nome",HttpStatus.BAD_REQUEST);
        }
        try{
            if(!arquivo.isEmpty()){
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(PathImages+String.valueOf(produtoBody.getId())+arquivo.getOriginalFilename());
                Files.write(caminho,bytes);
                produtoBody.setNomeImagem(String.valueOf(produtoBody.getId())+arquivo.getOriginalFilename());
                produtoRepository.save(produtoBody);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Produto cadastrado com sucesso",HttpStatus.OK);
    }

    @GetMapping("/produto")
    public ResponseEntity<Iterable<Produto>> getAll () {
        Iterable<Produto> products = produtoRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> update(@PathVariable long id,@RequestBody Produto produto){
        return produtoRepository.findById(id)
                .map(Pd -> {
                    Pd.setNome(produto.getNome());
                    Pd.setDescricao(produto.getDescricao());
                    Pd.setValor(produto.getValor());
                    Produto produtoUpdated = produtoRepository.save(Pd);
                    return ResponseEntity.ok().body(produtoUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/produto/img/{imagem}")
    @ResponseBody
    public byte[] getImage (@PathVariable("imagem") String imagem) throws IOException {
        File imagemArquivo = new File(PathImages+imagem);
        return Files.readAllBytes(imagemArquivo.toPath());
    }
}
