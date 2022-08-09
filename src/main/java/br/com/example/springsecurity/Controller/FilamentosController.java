package br.com.example.springsecurity.Controller;

import br.com.example.springsecurity.Model.Filamento;
import br.com.example.springsecurity.Model.User;
import br.com.example.springsecurity.Repository.FilamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "*")
public class FilamentosController {

    @Autowired
    FilamentosRepository filamentosRepository;

    @PostMapping(path ="/cadastar")
    public ModelAndView save(@RequestBody Filamento filamentoBody){

        Filamento filamento = new Filamento(filamentoBody.getTipo(),filamentoBody.getCor(),filamentoBody.getPeso());
        filamentosRepository.save(filamento);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/");
        return modelAndView;
    }


}
