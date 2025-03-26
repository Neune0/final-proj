package com.unocoding.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityDto {
    private String weekDay;
    private String timeDay;
}