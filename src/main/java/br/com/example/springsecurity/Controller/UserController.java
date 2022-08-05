package br.com.example.springsecurity.Controller;


import br.com.example.springsecurity.Model.Role;
import br.com.example.springsecurity.Model.User;
import br.com.example.springsecurity.Repository.RoleRepository;
import br.com.example.springsecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping(path ="/cadastar")
    public ModelAndView save(@RequestBody User newUser){
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User(newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()), newUser.getName(), newUser.getLast_name(), newUser.isEnabled(), newUser.getUsername() );
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/");
        return modelAndView;
    }

    @GetMapping(path = "/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cadastrar");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView  login (){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }




}
