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
@Table(name = "clienti")
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    private Ruolo ruolo = Ruolo.CLIENT;

    // the client can have a list of appointments with professionals
    // is relationship is one to many
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Appuntamento> appuntamenti;

    public Client(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}





