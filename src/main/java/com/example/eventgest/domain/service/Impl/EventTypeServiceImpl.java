package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.EventTypeRepository;
import com.example.eventgest.persistence.entity.Event;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class EventTypeServiceImpl implements EventTypeService {

    private final EventTypeRepository eventTypeRepository;
    private final EventRepository eventRepository;

    public EventTypeServiceImpl(EventTypeRepository eventTypeRepository, EventRepository eventRepository) {
        this.eventTypeRepository = eventTypeRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public boolean isEventTypeInUse(Long eventTypeId) {
        List<Event> events = eventRepository.findByEventTypeId(eventTypeId);
        return !events.isEmpty();
    }

    @Override
    public void deleteEventType(Long id) {
        if (isEventTypeInUse(id)) {
            throw new IllegalStateException("No se puede eliminar el tipo de evento porque está en uso.");
        }
        eventTypeRepository.deleteById(id);
    }


}














