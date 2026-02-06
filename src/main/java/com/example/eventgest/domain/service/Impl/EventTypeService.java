package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.dto.EventTypeDTO;
import com.example.eventgest.persistence.entity.EventType;

public interface EventTypeService {

        boolean isEventTypeInUse(Long eventTypeId);

        void deleteEventType(Long id);

        EventTypeDTO getById(Long id);
}