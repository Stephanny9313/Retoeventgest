package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.ParametHistosRepository;
import com.example.eventgest.domain.repository.ParameterRepository;
import com.example.eventgest.persistence.entity.Event;
import com.example.eventgest.persistence.entity.ParametHistos;
import com.example.eventgest.persistence.entity.Parameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ParametHistosServiceImpl {

    private final ParameterRepository parameterRepository;
    private final ParametHistosRepository parametHistosRepository;
    private final EventRepository eventRepository;

    public ParametHistosServiceImpl(
            ParameterRepository parameterRepository,
            ParametHistosRepository parametHistosRepository,
            EventRepository eventRepository) {
        this.parameterRepository = parameterRepository;
        this.parametHistosRepository = parametHistosRepository;
        this.eventRepository = eventRepository;
    }

    public void registerParameterChange(
            Long parameterId,
            String oldValue,
            String newValue,
            Long userId
    ) {
        Parameter parameter = parameterRepository.findById(parameterId)
                .orElseThrow(() ->
                        new RuntimeException("Parámetro no encontrado")
                );

        ParametHistos parametHistos= new ParametHistos();
        parametHistos.setParameter(parameter);
        parametHistos.setNewValue(newValue);
        parametHistos.setNewValue(oldValue);
        parametHistos.setPreviousValue(oldValue);
        parametHistos.setPreviousValue(oldValue);
        parametHistos.setDate(LocalDateTime.now().toLocalDate());





    }

    public void registerParameterUsage(
            Long parameterId,
            Long eventId
    ) {
        Parameter parameter = parameterRepository.findById(parameterId)
                .orElseThrow(() ->
                        new RuntimeException("Parámetro no encontrado")
                );

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new RuntimeException("Evento no encontrado")
                );

        ParametHistos parametHistos = new ParametHistos();
        parametHistos.setParameter(parameter);
        parametHistos.setUser(event.getUser());
        parametHistos.setDate(LocalDateTime.now().toLocalDate());



    }



}







