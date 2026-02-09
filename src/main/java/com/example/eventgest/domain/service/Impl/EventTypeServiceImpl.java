package com.example.eventgest.domain.service.Impl;


import com.example.eventgest.domain.dto.EventTypeDTO;
import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.EventTypeRepository;
import com.example.eventgest.persistence.entity.EventType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class EventTypeServiceImpl implements EventTypeService {

    private final EventTypeRepository eventTypeRepository;
    private final EventRepository eventRepository;

    public EventTypeServiceImpl(EventTypeRepository eventTypeRepository,
                                EventRepository eventRepository) {
        this.eventTypeRepository = eventTypeRepository;
        this.eventRepository = eventRepository;
    }

    public boolean isEventTypeInUse(Long eventTypeId) {
        return eventRepository.existsByEventType_Id(eventTypeId);
    }

    public void deleteEventType(Long id) {
        if (isEventTypeInUse(id)) {
            throw new IllegalStateException(
                    "No se puede eliminar el tipo de evento porque tiene eventos asociados"
            );
        }
        eventTypeRepository.deleteById(id);
    }

    @Override
    public EventTypeDTO getById(Long id) {
        return eventTypeRepository.findById(id)
                .map(entity -> new EventTypeDTO() {{
                    setId(entity.getId());
                    setTypeName(entity.getTypeName());
                }})
                .orElseThrow(() -> new RuntimeException("Tipo de evento no encontrado"));
    }

    @Override
    public EventTypeDTO create(EventTypeDTO dto) {
        EventType entity = new EventType();
        entity.setTypeName(dto.getTypeName());
        EventType saved = eventTypeRepository.save(entity);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public EventTypeDTO update(Long id, EventTypeDTO dto) {
        EventType entity = eventTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de evento no encontrado"));
        entity.setTypeName(dto.getTypeName());
        eventTypeRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }


    @Override
    public Iterable<EventTypeDTO> getAll() {
        return eventTypeRepository.findAll().stream()
                .map(e -> new EventTypeDTO() {{
                    setId(e.getId());
                    setTypeName(e.getTypeName());
                }})
                .collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) {
        deleteEventType(id); // ya tienes la lógica en tu método privado existente
    }


}



















