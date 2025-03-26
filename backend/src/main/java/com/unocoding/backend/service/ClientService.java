package com.unocoding.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.unocoding.backend.dto.ClientProfileDto;
import com.unocoding.backend.dto.UpdateClientProfileDto;
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

    public ClientProfileDto getClientProfile(String username) {
        Client client = clientRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        
        return mapClientToDto(client);
    }
    
    public ClientProfileDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        
        return mapClientToDto(client);
    }
    
    public List<ClientProfileDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::mapClientToDto)
                .collect(Collectors.toList());
    }
    
    public ClientProfileDto updateClientProfile(String username, UpdateClientProfileDto updateDto) {
        Client client = clientRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        
        if (updateDto.getEmail() != null && !updateDto.getEmail().isEmpty()) {
            client.setEmail(updateDto.getEmail());
        }
        
        if (updateDto.getFirstName() != null && !updateDto.getFirstName().isEmpty()) {
            client.setFirstName(updateDto.getFirstName());
        }
        
        if (updateDto.getLastName() != null && !updateDto.getLastName().isEmpty()) {
            client.setLastName(updateDto.getLastName());
        }
        
        client = clientRepository.save(client);
        return mapClientToDto(client);
    }
    
    public ClientProfileDto updateProfileImage(String username, String profileImageBase64) {
        Client client = clientRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        
        client.setProfileImageBase64(profileImageBase64);
        client = clientRepository.save(client);
        
        return mapClientToDto(client);
    }
    
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found");
        }
        clientRepository.deleteById(id);
    }
    
    private ClientProfileDto mapClientToDto(Client client) {
        ClientProfileDto dto = new ClientProfileDto();
        dto.setId(client.getId());
        dto.setUsername(client.getUsername());
        dto.setEmail(client.getEmail());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setProfileImageBase64(client.getProfileImageBase64());
        return dto;
    }
    
}