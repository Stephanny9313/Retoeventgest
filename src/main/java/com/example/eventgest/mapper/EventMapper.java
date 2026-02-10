package com.example.eventgest.mapper;


import com.example.eventgest.domain.dto.EventDTO;
import com.example.eventgest.persistence.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public static EventDTO toDto(Event event) {
        if (event == null) return null;
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setStartAt(event.getStartAt());
        dto.setEndAt(event.getEndAt());
        dto.setPlace(event.getPlace());
        dto.setMaxCapacity(event.getMaxCapacity());
        dto.setStatus(event.getStatus());
        dto.setOwnerId(event.getOwner() != null ? event.getOwner().getId() : null);
        dto.setProgramId(event.getProgram() != null ? event.getProgram().getId() : null);
        dto.setEventTypeId(event.getEventType() != null ? event.getEventType().getId() : null);
        return dto;
    }

    public static Event toEntity(EventDTO dto) {
        if (dto == null) return null;
        Event event = new Event();
        event.setId(dto.getId());
        event.getClient(dto.getClient());
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setStartAt(dto.getStartAt());
        event.setEndAt(dto.getEndAt());
        event.setPlace(dto.getPlace());
        event.setMaxCapacity(dto.getMaxCapacity());
        event.setStatus(dto.getStatus());
        // Relacionados se asignan en el servicio o repositorio
        return event;
    }
}
