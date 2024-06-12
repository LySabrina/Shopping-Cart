package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AuthProviderConfig {


    /**
     * Provide as a Bean that can be used within the application.
     * Use a DelegatingPasswordEnocder with different encryption options
     * Good for when we might change our password encoder (such as there's a vulnerability in the encoder)
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder(2,8,1,32,16));

        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }
}
