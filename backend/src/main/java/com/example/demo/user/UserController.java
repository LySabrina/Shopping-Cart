package com.example.demo.user;

import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.security.SecurityUser;
import com.example.demo.security.UserAuthenticationProvider;
import com.example.demo.security.UserSecurityManager;
import jakarta.servlet.http.HttpServletRequest;
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

    private final UserRepository userRepository;
    private final UserSecurityManager userSecurityManager;
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserSecurityManager userSecurityManager, UserAuthenticationProvider userAuthenticationProvider, UserService userService) {
        this.userRepository = userRepository;
        this.userSecurityManager = userSecurityManager;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
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
    public ResponseEntity<?> login(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        if (session != null) {
            String sessionId = session.getId();
            System.out.println(sessionId);
            return ResponseEntity.ok(sessionId);
        } else {
            System.out.println("failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to login");
        }
    }

    @GetMapping("/hello")
    public String hello(Authentication auth) {
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
