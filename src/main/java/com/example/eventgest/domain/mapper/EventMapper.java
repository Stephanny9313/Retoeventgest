package com.example.eventgest.domain.mapper;


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

    public EventDTO toDto(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }

    public Event toEntity(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }
}
