package com.example.eventgest.mapper;

import com.example.eventgest.domain.dto.EventDTO;
import com.example.eventgest.persistence.entity.Event;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    private final ModelMapper modelMapper;

    public EventMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // Configuración correcta del mapper
        this.modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    // ===============================
    // ENTITY → DTO
    // ===============================
    public EventDTO toDto(Event event) {
        if (event == null) return null;

        EventDTO dto = modelMapper.map(event, EventDTO.class);

        if (event.getOwner() != null) {
            dto.setOwnerId(event.getOwner().getId());
        }

        if (event.getProgram() != null) {
            dto.setProgramId(event.getProgram().getId());
        }

        if (event.getEventType() != null) {
            dto.setEventTypeId(event.getEventType().getId());
        }

        return dto;
    }

    // ===============================
    // DTO → ENTITY
    // ===============================
    public Event toEntity(EventDTO dto) {
        if (dto == null) return null;

        // Relaciones se asignan en el Service
        return modelMapper.map(dto, Event.class);
    }
}
