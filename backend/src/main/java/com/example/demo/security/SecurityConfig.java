package com.example.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;



@Configuration
public class SecurityConfig {


    private final UserManagerService userManagerService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserManagerService userManagerService, PasswordEncoder passwordEncoder) {
        this.userManagerService = userManagerService;
        this.passwordEncoder =passwordEncoder;
    }
    /**
     * Provide as a Bean that can be used within the applicaiton.
     * THe SecurityFilterChain configuration
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.csrf((c)-> c.disable()).authorizeHttpRequests((request) -> request.requestMatchers("/api/account/get").authenticated().anyRequest().permitAll()) .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) // Return 401 for unauthorized requests
        );;

        return http.build();
//        return http.authenticationProvider(authenticationProvider()).build();
    }


    //UserManagerService depends on the paswordencoder which is being made inside securityconfig
    // I should make it as its own class
    //thats why there cirucalr dependency
    // Since Srcurtiyconfig wants usermangerservice, but usermanagerservice needs ther passwordencoder

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new UserAuthenticationProvider(userManagerService, passwordEncoder);
    }


}
