package com.example.eventgest.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AuditDTO {


    private Long id;

    private String action;
    private String entity;
    private Long entityId;
    private String description;

    private LocalDate date;
    private LocalTime time;

    private Long userId;
}

