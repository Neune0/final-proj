package com.unocoding.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    // Additional fields for Professional
    private String profession;
    private String company;
    // Additional fields for Client
    private String firstName;
    private String lastName;
}