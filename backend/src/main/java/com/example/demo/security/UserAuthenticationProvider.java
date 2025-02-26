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
/**
 * Can have multiple AuthenticationProviders
 * ProviderManager will handle multiple AuthenticationProvider
 * We can have: UsernamePassword authenticationProvider or OAuth authenticationProvider
 * An Authentication Provider uses a UserDetailsService and PasswordEncoder to complete the authentication process
 */
public class UserAuthenticationProvider  implements AuthenticationProvider {

    private  final UserSecurityManager userSecurityManager;
    private final PasswordEncoder passwordEncoder;

    public UserAuthenticationProvider(UserSecurityManager userSecurityManager, PasswordEncoder passwordEncoder){
        this.userSecurityManager = userSecurityManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userSecurityManager.loadUserByUsername(username);
            if(passwordEncoder.matches(password, userDetails.getPassword())){
                return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
            }
            else{
                throw new BadCredentialsException("");
            }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
