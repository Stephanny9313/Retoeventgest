package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.persistence.entity.Event;

import java.util.List;

public class EventTyServiceImpl {

    private final EventRepository eventRepository;


    public EventTyServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
