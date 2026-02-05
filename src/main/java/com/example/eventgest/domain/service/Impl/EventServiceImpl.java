package com.example.eventgest.domain.service.Impl;


import com.example.eventgest.domain.dto.EventDTO;
import com.example.eventgest.domain.enums.EventStatus;
import com.example.eventgest.EventSpecification;
import com.example.eventgest.persistence.entity.Event;
import com.example.eventgest.domain.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDate;


@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EventDTO createEvent(EventDTO dto) {
        Event event = modelMapper.map(dto, Event.class);
        event.setStatus(EventStatus.DRAFT); // estado inicial
        Event saved = eventRepository.save(event);
        return modelMapper.map(saved, EventDTO.class);
    }

    @Override
    public EventDTO updateEvent(Long id, EventDTO dto) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        modelMapper.map(dto, existing); // actualizar campos
        Event saved = eventRepository.save(existing);
        return modelMapper.map(saved, EventDTO.class);
    }

    @Override
    public EventDTO publish(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        event.setStatus(EventStatus.PUBLISHED);
        Event saved = eventRepository.save(event);
        return modelMapper.map(saved, EventDTO.class);
    }

    @Override
    public EventDTO close(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        event.setStatus(EventStatus.CLOSED);
        Event saved = eventRepository.save(event);
        return modelMapper.map(saved, EventDTO.class);
    }

    @Override
    public EventDTO findById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        return modelMapper.map(event, EventDTO.class);
    }

    @Override
    public Page<EventDTO> findFiltered(String status, Long programId, LocalDate dateFrom, LocalDate dateTo, java.awt.print.Pageable pageable) {
        return null;
    }

    @Override
    public Page<EventDTO> findFiltered(String status, Long programId, LocalDate dateFrom, LocalDate dateTo, Pageable pageable) {
        return eventRepository.findAll(
                EventSpecification.filter(status, programId, dateFrom, dateTo),
                pageable
        ).map(e -> modelMapper.map(e, EventDTO.class));
    }
}


