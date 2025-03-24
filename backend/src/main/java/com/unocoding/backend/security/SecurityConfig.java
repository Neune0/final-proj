package com.unocoding.backend.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.unocoding.backend.models.Admin;
import com.unocoding.backend.repository.AdminRepository;
import com.unocoding.backend.repository.ClientRepository;
import com.unocoding.backend.repository.ProfessionalRepository;

// import com.example.festivalculturale.model.Utente;
// import com.example.festivalculturale.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final ProfessionalRepository professionalRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Cerca prima tra gli admin
            Optional<Admin> admin = adminRepository.findByUsername(username);
            if (admin.isPresent()) {
                return buildUserDetails(admin.get().getUsername(), admin.get().getPassword(), 
                                      admin.get().getRuolo().toString());
            }
            
            // Poi cerca tra i client e professional se necessario
            // [Qui implementazione per gli altri tipi di utente]
            
            throw new UsernameNotFoundException("Utente non trovato: " + username);
        };
    }

    private UserDetails buildUserDetails(String username, String password, String role) {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
        return new org.springframework.security.core.userdetails.User(
            username, password, Collections.singletonList(authority));
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .authenticationProvider(authenticationProvider());

        return http.build();
    }

    
}