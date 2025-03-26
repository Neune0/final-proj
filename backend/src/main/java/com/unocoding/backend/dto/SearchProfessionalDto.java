package com.unocoding.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchProfessionalDto {
    private String profession;
    private String company;
    private Double maxPricePerHour;
    private String availabilityDay;
    private String availabilityTime;
}