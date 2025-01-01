package com.example.demo.security;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Detailing with User
 * Although the method says by "username", in reality, I am using the email as a substitue for the username
 */
@Component
public class UserSecurityManager implements UserDetailsManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSecurityManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserDetails user) {
        if (user instanceof SecurityUser) {
            User u = ((SecurityUser) user).getUser();
            String encryptPassword = passwordEncoder.encode(user.getPassword());
            u.setPassword(encryptPassword);
            userRepository.save(u);
        }
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByEmail(username).isPresent();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUser(user.get());
    }
}
