package com.unocoding.backend.repository;

import com.unocoding.backend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);
}