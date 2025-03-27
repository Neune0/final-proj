package com.unocoding.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.unocoding.backend.dto.AvailabilityDto;
import com.unocoding.backend.dto.ProfessionalProfileDto;
import com.unocoding.backend.dto.SearchProfessionalDto;
import com.unocoding.backend.dto.UpdateProfessionalProfileDto;
import com.unocoding.backend.models.Disponibilita;
import com.unocoding.backend.service.ProfessionalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/professionals")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    // Public endpoints
    
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ProfessionalProfileDto>> getAllProfessionals() {
        List<ProfessionalProfileDto> professionals = professionalService.getAllProfessionals();
        return ResponseEntity.ok(professionals);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProfessionalProfileDto> getProfessionalById(@PathVariable Long id) {
        ProfessionalProfileDto professional = professionalService.getProfessionalById(id);
        return ResponseEntity.ok(professional);
    }

    @GetMapping("/search")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ProfessionalProfileDto>> searchProfessionals(SearchProfessionalDto searchCriteria) {
        List<ProfessionalProfileDto> results = professionalService.searchProfessionals(searchCriteria);
        return ResponseEntity.ok(results);
    }

    // Professional-only endpoints
    
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('PROFESSIONAL')")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProfessionalProfileDto> getCurrentProfessionalProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ProfessionalProfileDto profile = professionalService.getProfessionalProfile(username);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    @PreAuthorize("hasAuthority('PROFESSIONAL')")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProfessionalProfileDto> updateProfessionalProfile(
            @RequestBody UpdateProfessionalProfileDto updateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ProfessionalProfileDto updatedProfile = professionalService.updateProfessionalProfile(username, updateDto);
        return ResponseEntity.ok(updatedProfile);
    }

    @PutMapping("/profile/image")
    @PreAuthorize("hasAuthority('PROFESSIONAL')")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProfessionalProfileDto> updateProfileImage(@RequestBody String profileImageBase64) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ProfessionalProfileDto updatedProfile = professionalService.updateProfileImage(username, profileImageBase64);
        return ResponseEntity.ok(updatedProfile);
    }

    @PostMapping("/availability")
    @PreAuthorize("hasAuthority('PROFESSIONAL')")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProfessionalProfileDto> addAvailability(@RequestBody AvailabilityDto availabilityDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ProfessionalProfileDto updatedProfile = professionalService.addAvailability(username, availabilityDto);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("/availability")
    @PreAuthorize("hasAuthority('PROFESSIONAL')")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Disponibilita>> getAvailability() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Disponibilita> availability = professionalService.getAvailability(username);
        return ResponseEntity.ok(availability);
    }

    @DeleteMapping("/availability/{index}")
    @PreAuthorize("hasAuthority('PROFESSIONAL')")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProfessionalProfileDto> removeAvailability(@PathVariable int index) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ProfessionalProfileDto updatedProfile = professionalService.removeAvailability(username, index);
        return ResponseEntity.ok(updatedProfile);
    }
}