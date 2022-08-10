package br.com.example.springsecurity.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException() {
        super("Usuário não encontrado");
    }
}
