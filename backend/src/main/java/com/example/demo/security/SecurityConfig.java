package com.example.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;


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
        http.authorizeHttpRequests((request) -> request.requestMatchers("/api/account/get").authenticated().anyRequest().permitAll()).httpBasic(Customizer.withDefaults());

        return http.authenticationProvider(authenticationProvider()).build();

//        http.httpBasic(Customizer.withDefaults());
//
//        http.authenticationProvider(authenticationProvider());
//
//        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
//
//        return http.build();
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
