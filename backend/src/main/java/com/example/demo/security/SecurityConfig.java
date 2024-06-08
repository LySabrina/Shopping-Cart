package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig {

    /**
     * Provide as a Bean that can be used within the applicaiton.
     * THe SecurityFilterChain configuration
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.csrf().disable().authorizeHttpRequests().anyRequest().permitAll();
        return http.build();
    }

    /**
     * Provide as a Bean that can be used within the application.
     * Use a DelegatingPasswordEnocder with different encryption options
     * Good for when we might change our password encoder (such as there's a vulnerability in the encoder)
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder  passwordEncoder(){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder(2,8,1,32,16));

        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }
}
