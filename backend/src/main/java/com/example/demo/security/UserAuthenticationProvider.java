package com.example.demo.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider  implements AuthenticationProvider {

    private  final UserManagerService userManagerService;
    private final PasswordEncoder passwordEncoder;

    public UserAuthenticationProvider(UserManagerService userManagerService, PasswordEncoder passwordEncoder){
        this.userManagerService = userManagerService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();


            UserDetails userDetails = userManagerService.loadUserByUsername(username);
            if(passwordEncoder.matches(password, userDetails.getPassword())){
                return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
            }
            else{
                throw new BadCredentialsException("Invalid Credentials");
            }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
