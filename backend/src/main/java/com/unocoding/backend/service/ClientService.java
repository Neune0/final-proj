package com.unocoding.backend.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.unocoding.backend.models.Client;
import com.unocoding.backend.repository.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerClient(String username, String password, String email, String firstName, String lastName) {
        clientRepository.save(new Client(username, passwordEncoder.encode(password), email, firstName, lastName));
    }

    

    

    

    
}