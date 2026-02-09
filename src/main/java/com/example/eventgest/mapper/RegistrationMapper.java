package com.example.eventgest.mapper;


import com.example.eventgest.domain.dto.RegistrationDTO;
import com.example.eventgest.persistence.entity.Event;
import com.example.eventgest.persistence.entity.Participant;
import com.example.eventgest.persistence.entity.Registration;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {



    private final ModelMapper modelMapper;

    public RegistrationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RegistrationDTO toDto(Registration registration) {
        RegistrationDTO dto = new RegistrationDTO();
        dto.setId(registration.getId());
        dto.setEventId(registration.getEvent().getId());
        dto.setParticipantId(registration.getParticipant().getId());
        dto.setRegistrationDate(registration.getRegistrationDate());
        dto.setAttendance(registration.getAttendance());
        return dto;
    }

    public Registration toEntity(RegistrationDTO dto, Event event, Participant participant) {
        Registration registration = new Registration();
        registration.setId(dto.getId());
        registration.setEvent(event);
        registration.setParticipant(participant);
        registration.setRegistrationDate(dto.getRegistrationDate());
        registration.setAttendance(dto.getAttendance());
        return registration;
    }
}

