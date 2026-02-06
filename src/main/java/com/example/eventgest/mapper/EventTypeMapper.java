package com.example.eventgest.mapper;

import com.example.eventgest.domain.dto.EventTypeDTO;
import com.example.eventgest.persistence.entity.EventType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventTypeMapper {

    private final ModelMapper modelMapper;

    public EventTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EventTypeDTO toDto(EventType entity) {
        return modelMapper.map(entity, EventTypeDTO.class);
    }

    public EventType toEntity(EventTypeDTO dto) {
        return modelMapper.map(dto, EventType.class);
    }
}
