package com.unocoding.backend.dto;

import java.util.List;
import com.unocoding.backend.models.Disponibilita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalProfileDto {
    private Long id;
    private String username;
    private String email;
    private String profession;
    private String company;
    private double pricePerHour;
    private String description;
    private String profileImageBase64;
    private List<Disponibilita> disponibilita;
}