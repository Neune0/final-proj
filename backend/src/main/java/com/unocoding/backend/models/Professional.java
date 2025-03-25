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

    private Ruolo ruolo = Ruolo.PROFESSIONAL;

    private List<Disponibilita> disponibilita;

   // the professional can have a list of appointments with clients
    // is relationship is one to many
    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private List<Appuntamento> appuntamenti;

}
