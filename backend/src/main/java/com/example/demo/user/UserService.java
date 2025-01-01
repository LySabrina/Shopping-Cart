package com.example.demo.user;

import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.security.UserSecurityManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    public UserService(UserSecurityManager userSecurityManager, UserRepository userRepository) {
        this.userSecurityManager = userSecurityManager;
        this.userRepository = userRepository;
    }

    public void registerUser(UserDetails userDetails) throws UserAlreadyExistException {
        if (userSecurityManager.userExists(userDetails.getUsername())) {
            throw new UserAlreadyExistException(userDetails.getUsername());
        }
        userSecurityManager.createUser(userDetails);
    }

    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(u -> new UserDTO(u.getFname(), u.getLname()))
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

}
