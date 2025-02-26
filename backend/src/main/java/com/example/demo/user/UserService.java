package com.example.demo.user;

import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.security.UserAuthenticationProvider;
import com.example.demo.security.UserSecurityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Difference between UserService + UserDetailsManager
 * - UserService focus on general business logic
 * --> Can be fetching User Profile, fetching User settings, user registration (better to use in combination with UserDetailsMangaer )
 * - UserDetailsManager sole focus on security --> registration, user account management, authentication, updating password
 * <p>
 * TLDR: UserService = general user methods. UserDetailsManager = registration, account management
 */
@Service
public class UserService {
    private final UserSecurityManager userSecurityManager;
    private final UserRepository userRepository;
    private final UserAuthenticationProvider userAuthenticationProvider;

    public UserService(UserSecurityManager userSecurityManager, UserRepository userRepository,
                        UserAuthenticationProvider userAuthenticationProvider
                       ) {
        this.userSecurityManager = userSecurityManager;
        this.userRepository = userRepository;
        this.userAuthenticationProvider = userAuthenticationProvider;

    }

    public void registerUser(UserDetails userDetails) throws UserAlreadyExistException {
        if (userSecurityManager.userExists(userDetails.getUsername())) {
            throw new UserAlreadyExistException(userDetails.getUsername());
        }
        userSecurityManager.createUser(userDetails);
    }

    public ResponseEntity<String> login(LoginRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(requestDTO.getUsername(), requestDTO.getPassword());
        Authentication authenticationResponse = userAuthenticationProvider.authenticate(authenticationRequest);
        return ResponseEntity.status(200).body("Successfully Logged In");
    }

    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(u -> new UserDTO(u.getFname(), u.getLname()))
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> dtos = users.stream().map((item)-> new UserDTO(item.getFname(), item.getLname())).toList();
        return dtos;
    }

}
