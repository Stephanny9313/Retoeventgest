package com.example.eventgest.domain.dto;

import com.example.eventgest.domain.enums.EventStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventDTO {

    private Long Id;
    private String eventName;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer guestCount;
    private String location;
    private EventStatus status;
    private Integer maxCapacity;

    // Relaciones (solo Ids)
    private Long userId;
    private Long programId;
    private Long eventTypeId;
}


