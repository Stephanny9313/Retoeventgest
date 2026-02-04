package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.ParametHistoRepository;
import com.example.eventgest.domain.repository.ParameterRepository;

public class ParametHistoServiceImpl {

    private final ParameterRepository parameterRepository;
    private final ParametHistoRepository parametHistoRepository;
    private final EventRepository eventRepository;


    public ParametHistoServiceImpl(ParameterRepository parameterRepository, ParametHistoRepository parametHistoRepository, EventRepository eventRepository) {
        this.parameterRepository = parameterRepository;
        this.parametHistoRepository = parametHistoRepository;
        this.eventRepository = eventRepository;
    }
}
