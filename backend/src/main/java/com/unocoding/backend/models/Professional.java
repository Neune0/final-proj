package com.unocoding.backend.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "professionali")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String profession;
    private String company;
    private double pricePerHour;
    private String description;

    @Column(name = "profile_image", columnDefinition = "LONGTEXT")
    private String profileImageBase64;

    private Ruolo ruolo = Ruolo.PROFESSIONAL;

    // richieste di appuntamento ricevute
    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private List<PendingRequest> pendingRequests;

    @ElementCollection
    @CollectionTable(name = "professional_disponibilita", joinColumns = @JoinColumn(name = "professional_id"))
    private List<Disponibilita> disponibilita;

    public Professional(String username, String password, String email, String profession, String company) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profession = profession;
        this.company = company;
    }

}
