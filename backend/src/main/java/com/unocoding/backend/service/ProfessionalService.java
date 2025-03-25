package com.unocoding.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    
}
