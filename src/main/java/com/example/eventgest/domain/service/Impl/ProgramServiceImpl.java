package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.ProgramRepository;

public class ProgramServiceImpl {

    private final ProgramRepository programRepository;
    private final EventRepository eventRepository;


    public ProgramServiceImpl(ProgramRepository programRepository, EventRepository eventRepository) {
        this.programRepository = programRepository;
        this.eventRepository = eventRepository;
    }
}
