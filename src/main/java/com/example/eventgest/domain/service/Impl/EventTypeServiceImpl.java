package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.dto.EventTypeDTO;
import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.EventTypeRepository;
import com.example.eventgest.domain.service.Impl.EventTypeService;
import com.example.eventgest.mapper.EventTypeMapper;
import com.example.eventgest.persistence.entity.EventType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventTypeServiceImpl implements EventTypeService {

    private final EventTypeRepository eventTypeRepository;
    private final EventRepository eventRepository;
    private final EventTypeMapper mapper;

    public EventTypeServiceImpl(
            EventTypeRepository eventTypeRepository,
            EventRepository eventRepository,
            EventTypeMapper mapper
    ) {
        this.eventTypeRepository = eventTypeRepository;
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean isEventTypeInUse(Long eventTypeId) {
        return eventRepository.existsByEventTypeId(eventTypeId);
    }

    @Override
    public void deleteEventType(Long id) {

        if (isEventTypeInUse(id)) {
            throw new IllegalStateException("No se puede eliminar: tipo de evento en uso");
        }

        if (!eventTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("Tipo de evento no encontrado");
        }

        eventTypeRepository.deleteById(id);
    }

    @Override
    public EventTypeDTO getById(Long id) {
        EventType entity = eventTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de evento no encontrado"));

        return mapper.toDto(entity);
    }
}


















