package com.example.eventgest.domain.dto;

import com.example.eventgest.domain.enums.EventStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String place;
    private Integer maxCapacity;
    private EventStatus status;
    private Long programId;
    private Long eventTypeId;
    private Long ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
