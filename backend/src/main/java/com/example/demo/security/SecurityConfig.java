package com.example.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

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
        //I need HTTP Basic to enable the Username and password mechanics
        //When we do httpBasic() Spring Security is creating a BasicAuthFilter in the SecurityFilterchain
        // AllowedOrigins makes it easier so you don't have to add @CrossOrigin on every controller you make
        http.cors(c ->{
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(
                        List.of("http://localhost:5173")
                );
                config.setAllowedMethods(
                        List.of("GET", "POST")
                );
                config.setAllowedHeaders(List.of("*"));
                return config;
            };
            c.configurationSource(source);
        });

        http.csrf((c)-> c.disable());
        http.authorizeHttpRequests(
                (request) -> request.requestMatchers("/api/account/get")
                        .authenticated().anyRequest().permitAll())
                .formLogin(formLogin -> formLogin.loginPage("http://localhost:5173/account/login"));
        return http.authenticationProvider(authenticationProvider).build();
    }




}
