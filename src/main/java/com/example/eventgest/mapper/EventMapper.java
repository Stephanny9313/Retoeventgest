package com.example.eventgest.mapper;


import com.example.eventgest.domain.dto.EventDTO;
import com.example.eventgest.persistence.entity.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    private final ModelMapper modelMapper;

    public EventMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Convierte entidad a DTO
    public EventDTO toDto(Event event) {
        if (event == null) return null;

        EventDTO dto = modelMapper.map(event, EventDTO.class);

        // Asignar IDs de relaciones
        if (event.getUser() != null) dto.setUserId(event.getUser().getId());
        if (event.getProgram() != null) dto.setProgramId(event.getProgram().getId());
        if (event.getEventType() != null) dto.setEventTypeId(event.getEventType().getId());

        return dto;
    }

    // Convierte DTO a entidad
    public Event toEntity(EventDTO dto) {
        if (dto == null) return null;

        Event event = modelMapper.map(dto, Event.class);

        // NOTA: Relaciones (User, Program, EventType) se asignan en el ServiceImpl usando sus IDs
        return event;
    }
}
