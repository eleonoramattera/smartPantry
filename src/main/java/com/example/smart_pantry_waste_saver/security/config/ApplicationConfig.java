package com.example.smart_pantry_waste_saver.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ApplicationConfig {

    // 1. IL DATABASE DEGLI UTENTI (Per ora in memoria)
    // Questo risolve l'errore sul filtro! Definiamo un utente di test.
    @Bean
    public UserDetailsService userDetailsService() {
        // Creiamo un utente temporaneo con username "admin@test.com" e password "password123"
        UserDetails admin = User.builder()
                .username("admin") //queste sono le credenziali che devo inserire su postman quando faccio login di un utente che in realtà non esiste. mi serve solo come esempio e mi crea il jwt token
                .password(passwordEncoder().encode("1111")) // Cifriamo la password
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    // 2. IL GESTORE DELLE PASSWORD
    // Usiamo BCrypt, lo standard per cifrare le password in modo sicuro (non si salvano mai in chiaro!)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 3. IL PROVIDER DI AUTENTICAZIONE
    // Mette insieme il cercatore di utenti (UserDetailsService) e il cifratore di password
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // 4. L'AUTHENTICATION MANAGER
    // È il motore centrale di Spring Security che controlla se email e password inviate nel login sono corrette
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

