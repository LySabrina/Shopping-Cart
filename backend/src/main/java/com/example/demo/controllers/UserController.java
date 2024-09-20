package com.example.demo.controllers;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityUser;
import com.example.demo.security.UserAuthenticationProvider;
import com.example.demo.security.UserManagerService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class UserController {

    private final UserRepository userRepository;
    private final UserManagerService userManagerService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    public UserController(UserRepository userRepository, UserManagerService userManagerService, UserAuthenticationProvider userAuthenticationProvider) {
        this.userRepository = userRepository;
        this.userManagerService = userManagerService;
        this.userAuthenticationProvider = userAuthenticationProvider;
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


    @GetMapping("/hello")
    public String hello(Authentication auth){
        return "Hello, " + auth.getName() + "!~";
    }

    /**
     * This would be good if using Session Authentication
     * If using Basic Auth, there's no point in having a "login" function because every protected resource would need
     * the credentials be sent for request each time
     * Implement later when adopting Session Auth
     */
//    @PostMapping
//    public ResponseEntity<String> login(@RequestHeader("Authorization") String authorization){
//        String[] pair = new String(Base64.getDecoder().decode(authorization)).split(":");   // converts a byte[] into a String
//        String username = pair[0];
//        String password = pair[1];
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable int id){
//        User u = userManagerService.getUser(id);
//        if(u == null){
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        UserDTO dto =
//    }


}
