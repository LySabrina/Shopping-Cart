package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityUser;
import com.example.demo.security.UserManagerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserManagerService userManagerService;

    public UserController(UserRepository userRepository, UserManagerService userManagerService) {
        this.userRepository = userRepository;
        this.userManagerService = userManagerService;
    }

    @GetMapping("/get")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/getUser/")
    public UserDetails getUserByName(@RequestParam(name="username") String username){
        return userManagerService.loadUserByUsername(username);
    }
    @PostMapping("/add")
    public String addUser(){
        User u = new User("test", "email", "password", "READ");
        SecurityUser securityUser = new SecurityUser(u);
        userManagerService.createUser(securityUser);
        return "Added user";
    }

}
