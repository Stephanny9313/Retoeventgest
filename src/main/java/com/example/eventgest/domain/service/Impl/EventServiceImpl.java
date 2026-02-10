package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.audit.Auditable;
import com.example.eventgest.domain.dto.EventDTO;
import com.example.eventgest.domain.enums.EventStatus;
import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.EventTypeRepository;
import com.example.eventgest.domain.repository.ProgramRepository;
import com.example.eventgest.domain.repository.UserRepository;
import com.example.eventgest.persistence.entity.Event;
import com.example.eventgest.persistence.entity.User;
import com.example.eventgest.EventSpecification;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getName() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no autenticado");
        }
        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));
        return user.getId();
    }

    @Override
    @Auditable(action = "CREAR", entity = "EVENTO")
    public EventDTO createEvent(EventDTO dto) {
        Long userId = getCurrentUserId();

        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new IllegalArgumentException("El título del evento es obligatorio");
        }
        if (dto.getPlace() == null || dto.getPlace().isBlank()) {
            throw new IllegalArgumentException("El lugar del evento es obligatorio");
        }
        if (dto.getStartAt() == null || dto.getEndAt() == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias");
        }
        if (dto.getStartAt().isAfter(dto.getEndAt())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
        if (dto.getProgramId() == null) {
            throw new IllegalArgumentException("El programa del evento es obligatorio");
        }
        if (dto.getMaxCapacity() == null) {
            throw new IllegalArgumentException("La capacidad máxima del evento es obligatoria");
        }
        if (dto.getMaxCapacity() <= 0) {
            throw new IllegalArgumentException("La capacidad máxima del evento debe ser mayor a cero");
        }
        if (dto.getEventTypeId() == null) {
            throw new IllegalArgumentException("El tipo de evento es obligatorio");
        }
        if (dto.getDescription() == null || dto.getDescription().isBlank()) {
            throw new IllegalArgumentException("La descripción del evento es obligatoria");
        }
        if (dto.getDescription().length() > 500) {
            throw new IllegalArgumentException("La descripción del evento no puede exceder los 500 caracteres");
        }

        Event event = modelMapper.map(dto, Event.class);
        event.setStatus(EventStatus.DRAFT);
        event.setProgram(programRepository.findById(dto.getProgramId())
                .orElseThrow(() -> new RuntimeException("Programa no encontrado")));
        event.setEventType(eventTypeRepository.findById(dto.getEventTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de evento no encontrado")));
        event.setOwner(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        Event saved = eventRepository.save(event);
        return modelMapper.map(saved, EventDTO.class);
    }

    @Override
    @Auditable(action = "EDITAR", entity = "EVENTO")
    public EventDTO updateEvent(Long id, EventDTO dto) {
        Long userId = getCurrentUserId();
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        if (existing.getStatus() == EventStatus.CLOSED) {
            throw new IllegalStateException("No se puede editar un evento cerrado");
        }
        modelMapper.map(dto, existing);
        Event saved = eventRepository.save(existing);
        return modelMapper.map(saved, EventDTO.class);
    }

    @Override
    @Auditable(action = "PUBLICAR", entity = "EVENTO")
    public EventDTO publish(Long id) {
        Long userId = getCurrentUserId();
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        if (event.getStatus() != EventStatus.DRAFT) {
            throw new IllegalStateException("Solo eventos en borrador pueden publicarse");
        }
        if (event.getStartAt() == null || event.getEndAt() == null) {
            throw new IllegalStateException("El evento debe tener fechas definidas");
        }
        event.setStatus(EventStatus.PUBLISHED);
        Event saved = eventRepository.save(event);
        return modelMapper.map(saved, EventDTO.class);
    }

    @Override
    @Auditable(action = "CERRAR", entity = "EVENTO")
    public EventDTO close(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        if (event.getStatus() != EventStatus.PUBLISHED) {
            throw new IllegalStateException("Solo eventos publicados pueden cerrarse");
        }
        event.setStatus(EventStatus.CLOSED);
        return modelMapper.map(eventRepository.save(event), EventDTO.class);
    }

    @Override
    public EventDTO findById(Long id) {
        return eventRepository.findById(id)
                .map(e -> modelMapper.map(e, EventDTO.class))
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

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