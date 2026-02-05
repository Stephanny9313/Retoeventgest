package com.example.eventgest.domain.service.Impl;

public interface EventTypeService {


        boolean isEventTypeInUse(Long eventTypeId);

        void deleteEventType(Long id);



}
