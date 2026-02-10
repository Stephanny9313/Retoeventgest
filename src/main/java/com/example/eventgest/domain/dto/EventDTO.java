package com.example.eventgest.domain.dto;

import com.example.eventgest.domain.enums.EventStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate startAt;
    private LocalDate endAt;
    private String place;
    private String client;
    private Integer maxCapacity;
    private EventStatus status;
    private Long programId;
    private Long eventTypeId;
    private Long ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
