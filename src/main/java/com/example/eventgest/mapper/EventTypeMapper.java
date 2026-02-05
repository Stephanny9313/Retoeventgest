package com.example.eventgest.mapper;


import com.example.eventgest.domain.dto.EventTypeDTO;
import com.example.eventgest.persistence.entity.EventType;
import org.modelmapper.ModelMapper;

public class EventTypeMapper {

    private final ModelMapper modelMapper;

    public EventTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EventTypeDTO toDto(EventType eventType) {
        return modelMapper.map(eventType, EventTypeDTO.class);
    }

    public EventType toEntity(EventTypeDTO dto) {
        return modelMapper.map(dto, EventType.class);
    }

    public Long toId(EventType eventType) {
        if (eventType == null) return null;
        return eventType.getId();
    }

}


