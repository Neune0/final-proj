package com.unocoding.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pending_requests")
public class PendingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;
    
    private LocalDateTime requestDate;
    private String proposedMeetingTime;
    private String message;
    
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;
}

enum RequestStatus {
    PENDING,
    ACCEPTED,
    REJECTED
}
