package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.persistence.entity.EventType;

public interface EventTypeService {


        boolean isEventTypeInUse(Long eventTypeId);

        void deleteEventType(Long id);


        EventType getEventTypeById(Long id);
}
