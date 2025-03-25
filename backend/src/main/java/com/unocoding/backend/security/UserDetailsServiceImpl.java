package com.unocoding.backend.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unocoding.backend.models.Admin;
import com.unocoding.backend.models.Client;
import com.unocoding.backend.models.Professional;
import com.unocoding.backend.repository.AdminRepository;
import com.unocoding.backend.repository.ClientRepository;
import com.unocoding.backend.repository.ProfessionalRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final ProfessionalRepository professionalRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if user exists as an Admin
        Admin admin = adminRepository.findByUsername(username).orElse(null);
        if (admin != null) {
            return buildUserDetails(admin.getUsername(), admin.getPassword(), admin.getRuolo().name());
        }
        
        // Check if user exists as a Client
        Client client = clientRepository.findByUsername(username).orElse(null);
        if (client != null) {
            return buildUserDetails(client.getUsername(), client.getPassword(), client.getRuolo().name());
        }
        
        // Check if user exists as a Professional
        Professional professional = professionalRepository.findByUsername(username).orElse(null);
        if (professional != null) {
            return buildUserDetails(professional.getUsername(), professional.getPassword(), professional.getRuolo().name());
        }
        
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
    
    private UserDetails buildUserDetails(String username, String password, String role) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        
        return new User(username, password, authorities);
    }
}