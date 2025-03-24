package com.unocoding.backend.repository;

import com.unocoding.backend.models.Appuntamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppuntamentoRepository extends JpaRepository<Appuntamento, Long> {
    
}