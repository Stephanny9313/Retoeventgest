package com.example.eventgest.domain.dto;

import com.example.eventgest.domain.enums.EventStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {

    private Long id;

    // ===============================
    // DATOS DEL EVENTO
    // ===============================

    private String title;
    private String description;
    private String place;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private Integer capacity;
    private EventStatus status;

    // ===============================
    // RELACIONES (SOLO IDS)
    // ===============================

    private Long programId;
    private Long eventTypeId;
    private Long ownerId;
}
