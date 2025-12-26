package com.realestate.crm_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Critical for POST requests via Postman
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // Ensure this covers /api/** 
                .anyRequest().authenticated()
                );
        return http.build();
    }

}
