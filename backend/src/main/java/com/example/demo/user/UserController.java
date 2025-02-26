package com.example.demo.user;

import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.security.SecurityUser;
import com.example.demo.security.UserAuthenticationProvider;
import com.example.demo.security.UserSecurityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class UserController {

    private final UserSecurityManager userSecurityManager;
    private final UserService userService;

    public UserController( UserSecurityManager userSecurityManager, UserService userService) {

        this.userSecurityManager = userSecurityManager;

        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/getUser/")
    public UserDTO getUserByName(@RequestParam(name = "email") String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegistrationDTO registrationDTO) throws UserAlreadyExistException {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(registrationDTO, User.class);
        SecurityUser securityUser = new SecurityUser(user);
        userService.registerUser(securityUser);
        return ResponseEntity.status(HttpStatus.OK).body("Registration Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO requestDTO,
        HttpServletRequest request, HttpServletResponse response, HttpSession httpSession
    ) {
        return userService.login(requestDTO,request,response, httpSession);
    }

}
