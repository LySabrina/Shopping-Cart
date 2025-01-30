package com.example.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class SecurityConfig {
    private final UserAuthenticationProvider authenticationProvider;

    public SecurityConfig( UserAuthenticationProvider authenticationProvider) {
        this.authenticationProvider  = authenticationProvider;
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
//        http.authorizeHttpRequests((requests)->
//                        requests.requestMatchers("/api/product/*").authenticated()
//                                .requestMatchers("/api/account/*").permitAll()
//
//                )
//                .authenticationProvider(authenticationProvider)
//                .sessionManagement(session -> session
//                .sessionFixation().migrateSession())
//
//                .csrf(csrf->csrf.disable());

        http.authorizeHttpRequests((request)->
                request.anyRequest().permitAll())
                .csrf((csrf)->csrf.disable())
        ;
        // Optional: Disable CSRF for stateless APIs (e.g., REST APIs)
        return http.build();
    }




}
