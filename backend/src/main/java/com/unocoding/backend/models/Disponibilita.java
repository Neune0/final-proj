package com.unocoding.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Disponibilita {
    private String weekDay;
    private String timeDay;
}
