package com.unocoding.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appuntamenti")
public class Appuntamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime dateTime;
    private Integer duration; // in minuti
    private String notes;
    
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}