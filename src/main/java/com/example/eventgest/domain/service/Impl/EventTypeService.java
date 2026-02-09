package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.dto.EventTypeDTO;

public interface EventTypeService {

        boolean isEventTypeInUse(Long eventTypeId);

        void deleteEventType(Long id);

        EventTypeDTO getById(Long id);

        EventTypeDTO create(EventTypeDTO dto);

        EventTypeDTO update(Long id, EventTypeDTO dto);

        Iterable<EventTypeDTO> getAll();

        void delete(Long id);
}