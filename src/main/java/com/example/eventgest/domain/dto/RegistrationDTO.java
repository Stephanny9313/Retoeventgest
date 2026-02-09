package com.example.eventgest.domain.dto;

import com.example.eventgest.domain.enums.AttendanceStatus;
import com.example.eventgest.persistence.entity.Event;
import com.example.eventgest.persistence.entity.Participant;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationDTO {

    private Long id;
    private Long eventId;
    private Long participantId;
    private LocalDate registrationDate;
    private AttendanceStatus attendance;

}
