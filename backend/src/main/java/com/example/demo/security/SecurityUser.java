package com.example.demo.security;

import com.example.demo.models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * User Information
 * Separate from User.java for clean code
 * This class deals with actions related to security
 */
@Data
public class SecurityUser implements UserDetails {
    private User user;

    public SecurityUser(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()-> user.getAuthority());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
         return user.getUsername();
    }
}
