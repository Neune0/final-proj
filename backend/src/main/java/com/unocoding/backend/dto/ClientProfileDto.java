package com.unocoding.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientProfileDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String profileImageBase64;
}