package com.unocoding.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfessionalProfileDto {
    private String email;
    private String profession;
    private String company;
    private Double pricePerHour;
    private String description;
}