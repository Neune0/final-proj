package com.unocoding.backend.repository;

import com.unocoding.backend.models.PendingRequest;
import com.unocoding.backend.models.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PendingRequestRepository extends JpaRepository<PendingRequest, Long> {
    List<PendingRequest> findByClientId(Long clientId);
    List<PendingRequest> findByProfessionalId(Long professionalId);
    List<PendingRequest> findByStatus(RequestStatus status);
    Optional<PendingRequest> findByIdAndClientId(Long id, Long clientId);
    Optional<PendingRequest> findByIdAndProfessionalId(Long id, Long professionalId);
}