package com.unocoding.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unocoding.backend.dto.AvailabilityDto;
import com.unocoding.backend.dto.ProfessionalProfileDto;
import com.unocoding.backend.dto.SearchProfessionalDto;
import com.unocoding.backend.dto.UpdateProfessionalProfileDto;
import com.unocoding.backend.models.Disponibilita;
import com.unocoding.backend.models.Professional;
import com.unocoding.backend.repository.ProfessionalRepository;

@Service
public class ProfessionalService {
    private final ProfessionalRepository professionalRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ProfessionalService(ProfessionalRepository professionalRepository, BCryptPasswordEncoder passwordEncoder) {
        this.professionalRepository = professionalRepository;
        this.passwordEncoder = passwordEncoder;
    }
    

    public void registerProfessional(String username, String password, String email, String profession,
            String company) {
        professionalRepository.save(new Professional(username, passwordEncoder.encode(password), email, profession, company));
        
    }
    public List<ProfessionalProfileDto> getAllProfessionals() {
        return professionalRepository.findAll().stream()
                .map(this::mapProfessionalToDto)
                .collect(Collectors.toList());
    }
    
    public ProfessionalProfileDto getProfessionalById(Long id) {
        Professional professional = professionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        
        return mapProfessionalToDto(professional);
    }
    
    public ProfessionalProfileDto getProfessionalProfile(String username) {
        Professional professional = professionalRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        
        return mapProfessionalToDto(professional);
    }
    
    public ProfessionalProfileDto updateProfessionalProfile(String username, UpdateProfessionalProfileDto updateDto) {
        Professional professional = professionalRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        
        if (updateDto.getEmail() != null && !updateDto.getEmail().isEmpty()) {
            professional.setEmail(updateDto.getEmail());
        }
        
        if (updateDto.getProfession() != null && !updateDto.getProfession().isEmpty()) {
            professional.setProfession(updateDto.getProfession());
        }
        
        if (updateDto.getCompany() != null && !updateDto.getCompany().isEmpty()) {
            professional.setCompany(updateDto.getCompany());
        }
        
        if (updateDto.getPricePerHour() != null) {
            professional.setPricePerHour(updateDto.getPricePerHour());
        }
        
        if (updateDto.getDescription() != null) {
            professional.setDescription(updateDto.getDescription());
        }
        
        professional = professionalRepository.save(professional);
        return mapProfessionalToDto(professional);
    }
    
    public ProfessionalProfileDto updateProfileImage(String username, String profileImageBase64) {
        Professional professional = professionalRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        
        professional.setProfileImageBase64(profileImageBase64);
        professional = professionalRepository.save(professional);
        
        return mapProfessionalToDto(professional);
    }
    
    public ProfessionalProfileDto addAvailability(String username, AvailabilityDto availabilityDto) {
        Professional professional = professionalRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        
        Disponibilita newAvailability = new Disponibilita(
                availabilityDto.getWeekDay(), 
                availabilityDto.getTimeDay());
        
        if (professional.getDisponibilita() == null) {
            professional.setDisponibilita(new ArrayList<>());
        }
        
        professional.getDisponibilita().add(newAvailability);
        professional = professionalRepository.save(professional);
        
        return mapProfessionalToDto(professional);
    }
    
    public List<Disponibilita> getAvailability(String username) {
        Professional professional = professionalRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        
        return professional.getDisponibilita();
    }
    
    public ProfessionalProfileDto removeAvailability(String username, int index) {
        Professional professional = professionalRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        
        if (professional.getDisponibilita() == null || 
            index < 0 || 
            index >= professional.getDisponibilita().size()) {
            throw new RuntimeException("Availability slot not found");
        }
        
        professional.getDisponibilita().remove(index);
        professional = professionalRepository.save(professional);
        
        return mapProfessionalToDto(professional);
    }
    
    public List<ProfessionalProfileDto> searchProfessionals(SearchProfessionalDto searchCriteria) {
        // In a real application, this would use more sophisticated search techniques
        // or a specific query method in the repository
        return professionalRepository.findAll().stream()
                .filter(p -> matchesCriteria(p, searchCriteria))
                .map(this::mapProfessionalToDto)
                .collect(Collectors.toList());
    }
    
    private boolean matchesCriteria(Professional professional, SearchProfessionalDto criteria) {
        boolean matches = true;
        
        if (criteria.getProfession() != null && !criteria.getProfession().isEmpty()) {
            matches = matches && professional.getProfession().toLowerCase()
                    .contains(criteria.getProfession().toLowerCase());
        }
        
        if (criteria.getCompany() != null && !criteria.getCompany().isEmpty()) {
            matches = matches && professional.getCompany().toLowerCase()
                    .contains(criteria.getCompany().toLowerCase());
        }
        
        if (criteria.getMaxPricePerHour() != null) {
            matches = matches && professional.getPricePerHour() <= criteria.getMaxPricePerHour();
        }
        
        // More complex availability search would go here
        
        return matches;
    }
    
    private ProfessionalProfileDto mapProfessionalToDto(Professional professional) {
        ProfessionalProfileDto dto = new ProfessionalProfileDto();
        dto.setId(professional.getId());
        dto.setUsername(professional.getUsername());
        dto.setEmail(professional.getEmail());
        dto.setProfession(professional.getProfession());
        dto.setCompany(professional.getCompany());
        dto.setPricePerHour(professional.getPricePerHour());
        dto.setDescription(professional.getDescription());
        dto.setProfileImageBase64(professional.getProfileImageBase64());
        dto.setDisponibilita(professional.getDisponibilita());
        return dto;
    }
    
}
