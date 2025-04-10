package com.unocoding.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.unocoding.backend.dto.ClientProfileDto;
import com.unocoding.backend.dto.UpdateClientProfileDto;
import com.unocoding.backend.service.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
@CrossOrigin
public class ClientController {

    private final ClientService clientService;

    // Endpoint to get current client profile
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<ClientProfileDto> getCurrentClientProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ClientProfileDto profile = clientService.getClientProfile(username);
        return ResponseEntity.ok(profile);
    }

    // Endpoint to update current client profile
    @PutMapping("/profile")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<ClientProfileDto> updateClientProfile(@RequestBody UpdateClientProfileDto updateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ClientProfileDto updatedProfile = clientService.updateClientProfile(username, updateDto);
        return ResponseEntity.ok(updatedProfile);
    }

    // Endpoint to update profile image
    @PutMapping("/profile/image")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<ClientProfileDto> updateProfileImage(@RequestBody String profileImageBase64) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ClientProfileDto updatedProfile = clientService.updateProfileImage(username, profileImageBase64);
        return ResponseEntity.ok(updatedProfile);
    }

}