package com.example.eventgest.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AuditDTO {

    private Long id;
    private String action;
    private LocalDate date;
    private LocalTime time;
    private String description;
}
