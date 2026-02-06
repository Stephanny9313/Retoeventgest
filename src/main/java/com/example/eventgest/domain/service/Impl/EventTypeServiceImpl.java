package com.example.eventgest.domain.service.Impl;


import com.example.eventgest.domain.dto.EventTypeDTO;
import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.EventTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return null;
    }
}


















