package br.com.example.springsecurity.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotExistsException extends RuntimeException{
    public ProductNotExistsException() {
        super("Produto não encontrado");
    }
}
