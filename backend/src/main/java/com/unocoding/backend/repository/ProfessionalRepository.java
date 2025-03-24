package com.unocoding.backend.repository;


import com.unocoding.backend.models.Professional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Optional<Professional> findByUsername(String username);
}