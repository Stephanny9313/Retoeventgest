package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.audit.Auditable;
import com.example.eventgest.domain.dto.EventDTO;
import com.example.eventgest.domain.enums.EventStatus;
import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.EventTypeRepository;
import com.example.eventgest.domain.repository.ProgramRepository;
import com.example.eventgest.domain.repository.UserRepository;
import com.example.eventgest.domain.service.Impl.EventService;
import com.example.eventgest.persistence.entity.Event;
import com.example.eventgest.persistence.entity.EventType;
import com.example.eventgest.persistence.entity.Program;
import com.example.eventgest.persistence.entity.User;
import com.example.eventgest.EventSpecification;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ProgramRepository programRepository;
    private final EventTypeRepository eventTypeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository,
                            ProgramRepository programRepository,
                            EventTypeRepository eventTypeRepository,
                            UserRepository userRepository,
                            ModelMapper modelMapper) {

        this.eventRepository = eventRepository;
        this.programRepository = programRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    // ===============================
    // CREATE
    // ===============================
    @Override
    @Auditable(action = "CREAR", entity = "EVENTO")

    public EventDTO createEvent(EventDTO dto, Long user_id) {

        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new IllegalArgumentException("El título del evento es obligatorio");
        }

        if (dto.getPlace() == null || dto.getPlace().isBlank()) {
            throw new IllegalArgumentException("El lugar del evento es obligatorio");
        }

        Event event = modelMapper.map(dto, Event.class);
        event.setStatus(EventStatus.DRAFT);

        event.setProgram(programRepository.findById(dto.getProgramId())
                .orElseThrow(() -> new RuntimeException("Programa no encontrado")));

        event.setEventType(eventTypeRepository.findById(dto.getEventTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de evento no encontrado")));

        event.setOwner(userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        Event saved = eventRepository.save(event);
        return modelMapper.map(saved, EventDTO.class);
    }

    // ===============================
    // UPDATE
    @Override
    @Auditable(action = "EDITAR", entity = "EVENTO")
    public EventDTO updateEvent(Long id, EventDTO dto, Long user_id) {

        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        if (existing.getStatus() == EventStatus.CLOSED) {
            throw new IllegalStateException("No se puede editar un evento cerrado");
        }

        modelMapper.map(dto, existing);
        Event saved = eventRepository.save(existing);

        return modelMapper.map(saved, EventDTO.class);
    }

    // ===============================
    // PUBLISH
    // ===============================
    @Override
    @Auditable(action = "PUBLICAR", entity = "EVENTO")
    public EventDTO publish(Long id, Long user_id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        if (event.getStatus() != EventStatus.DRAFT) {
            throw new IllegalStateException("Solo eventos en borrador pueden publicarse");
        }

        if (event.getStartAt() == null || event.getEndAt() == null) {
            throw new IllegalStateException("El evento debe tener fechas definidas");
        }

        event.setStatus(EventStatus.PUBLISHED);
        return modelMapper.map(eventRepository.save(event), EventDTO.class);
    }

    // ===============================
    // CLOSE
    // ===============================
    @Override
    @Auditable(action = "CERRAR", entity = "EVENTO")
    public EventDTO close(Long id, Long userId) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        if (event.getStatus() != EventStatus.PUBLISHED) {
            throw new IllegalStateException("Solo eventos publicados pueden cerrarse");
        }

        event.setStatus(EventStatus.CLOSED);
        return modelMapper.map(eventRepository.save(event), EventDTO.class);
    }

    // ===============================
    // FIND BY ID
    // ===============================
    @Override
    public EventDTO findById(Long id) {
        return eventRepository.findById(id)
                .map(e -> modelMapper.map(e, EventDTO.class))
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    // ===============================
    // FILTERS
    // ===============================
    @Override
    public Page<EventDTO> findFiltered(
            String status,
            Long programId,
            LocalDate dateFrom,
            LocalDate dateTo,
            Pageable pageable) {

        return eventRepository.findAll(
                EventSpecification.filter(status, programId, dateFrom, dateTo),
                pageable
        ).map(e -> modelMapper.map(e, EventDTO.class));
    }
}
