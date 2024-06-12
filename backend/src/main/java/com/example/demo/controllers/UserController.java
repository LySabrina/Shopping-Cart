package com.example.demo.controllers;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityUser;
import com.example.demo.security.UserManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
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

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDTO, User.class);
        SecurityUser securityUser = new SecurityUser(user);
        userManagerService.createUser(securityUser);
        return ResponseEntity.status(HttpStatus.OK).body("Registration Successful");
    }



}
