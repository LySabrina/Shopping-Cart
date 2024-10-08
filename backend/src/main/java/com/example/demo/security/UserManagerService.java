package com.example.demo.security;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagerService implements UserDetailsManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserManagerService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void createUser(UserDetails user) {
        if(user instanceof SecurityUser){
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

    public User getUser(int id){
        Optional<User> userOptional= userRepository.findById(id);
        if(userOptional.isEmpty()){
            return null;
        }
        return userOptional.get();
    }
    @Override
    public boolean userExists(String username) {
//        return userRepository.findByUsername(username) != null;
        return userRepository.findByEmail(username) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
        User user = userRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUser(user);
    }
}
