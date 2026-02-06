package com.example.eventgest.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParametHistoDTO {

    private Long id;                // corregido
    private String previousValue;
    private String newValue;
    private LocalDate date;

}

