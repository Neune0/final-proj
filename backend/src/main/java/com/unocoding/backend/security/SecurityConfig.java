package com.unocoding.backend.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// import com.example.festivalculturale.model.Utente;
// import com.example.festivalculturale.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    // private final UtenteRepository utenteRepository;

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return email -> {

    //         Optional<Utente> utente = utenteRepository.findByEmail(email);
    //         Utente utente2 = utente.get();
    //         if (utente2 == null) {
    //             throw new UsernameNotFoundException("Utente non trovato con email: " + email);
    //         }
    //         List<GrantedAuthority> authorities = Collections.singletonList(
    //                 new SimpleGrantedAuthority("ROLE_" + utente2.getRuolo().name()));

    //         return new org.springframework.security.core.userdetails.User(utente2.getEmail(), utente2.getPassword(),
    //                 authorities);
    //     };
    // }

    // @Bean
    // public DaoAuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    //     authProvider.setUserDetailsService(userDetailsService());
    //     authProvider.setPasswordEncoder(passwordEncoder());
    //     return authProvider;
    // }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //             .csrf(csrf -> csrf.disable())

    //             .authorizeHttpRequests(auth -> auth
    //                     .requestMatchers("/auth/**", "/css/**", "/js/**").permitAll()
    //                     .requestMatchers("/admin/**").hasRole("ADMIN")
    //                     .anyRequest().authenticated())

    //             .formLogin(form -> form
    //                     .loginPage("/auth/login")
    //                     .loginProcessingUrl("/auth/login")
    //                     .successHandler((request, response, authentication) -> {
    //                         String redirectUrl = "/prenotazioni/dashboard";
    //                         if (authentication.getAuthorities().stream()
    //                                 .anyMatch(
    //                                         grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
    //                             redirectUrl = "/admin/dashboard";
    //                         }
    //                         response.sendRedirect(redirectUrl);
    //                     })
    //                     .permitAll())
    //             .logout(logout -> logout
    //                     .logoutUrl("/auth/logout")
    //                     .logoutSuccessUrl("/auth/login?logout")
    //                     .permitAll())
    //             .authenticationProvider(authenticationProvider());

    //     return http.build();
    // }
}