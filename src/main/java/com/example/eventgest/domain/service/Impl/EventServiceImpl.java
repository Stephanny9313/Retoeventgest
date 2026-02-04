package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.*;
import com.example.eventgest.domain.service.Impl.EventService;
import com.example.eventgest.domain.service.Impl.AuditServiceImpl;
import com.example.eventgest.persistence.entity.*;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;




@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;
    private final EventTypeRepository eventTypeRepository;
    private final AuditServiceImpl auditService;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, ProgramRepository programRepository,
                            EventTypeRepository eventTypeRepository, AuditServiceImpl auditService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.programRepository = programRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.auditService = auditService;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + id));
    }

    @Override
    public List<Event> getAllEvents() {
        return List.of();
    }

    @Override
    public Event getEventById(Long id) {
        return null;
    }

    @Override
    public Event createEvent(Event event) {
        User user = userRepository.findById(event.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Program program = programRepository.findById(event.getProgram().getId())
                .orElseThrow(() -> new IllegalArgumentException("Program not found"));

        EventType eventType = eventTypeRepository.findById(event.getEventType().getId())
                .orElseThrow(() -> new IllegalArgumentException("EventType not found"));

        if (event.getGuestCount() > event.getMaxCapacity()) {
            throw new IllegalArgumentException("Guest count exceeds max capacity");
        }

        event.setUser(user);
        event.setProgram(program);
        event.setEventType(eventType);

        Event saved = eventRepository.save(event);

        public void registerAction (Long user_Id, String action, String description){
            if (user_Id == null) {
                throw new IllegalArgumentException("UserId cannot be null for auditing");
            }

            User users = userRepository.findById(user_Id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found for auditing"));

            Audit audit = new Audit();
            audit.setUser(user);
            audit.getAction();
            audit.getDescription();
            audit.setDate(LocalDate.now());
            audit.setTime(LocalTime.now());
            audit.setCreatedAt(LocalDateTime.now());

            auditRepository.save(audit);
        }
    }


    @Override
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + id));

        event.setEventName(eventDetails.getEventName());
        event.setDescription(eventDetails.getDescription());
        event.setDate(eventDetails.getDate());
        event.setStartTime(eventDetails.getStartTime());
        event.setEndTime(eventDetails.getEndTime());
        event.setGuestCount(eventDetails.getGuestCount());
        event.setLocation(eventDetails.getLocation());
        event.setStatus(eventDetails.getStatus());
        event.setMaxCapacity(eventDetails.getMaxCapacity());

        if (eventDetails.getUser() != null) {
            User user = userRepository.findById(eventDetails.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            event.setUser(user);
        }

        if (eventDetails.getProgram() != null) {
            Program program = programRepository.findById(eventDetails.getProgram().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Program not found"));
            event.setProgram(program);
        }

        if (eventDetails.getEventType() != null) {
            EventType eventType = eventTypeRepository.findById(eventDetails.getEventType().getId())
                    .orElseThrow(() -> new IllegalArgumentException("EventType not found"));
            event.setEventType(eventType);
        }

        Event updated = eventRepository.save(event);



        return updated;
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + id));
        eventRepository.delete(event);

    }

    @Override
    public Event create(Event event, Long userId) {
        return null;
    }

    @Override
    public Event update(Long eventId, Event event) {
        return null;
    }

    @Override
    public void delete(Long eventId) {

    }

    @Override
    public Event publish(Long event_Id, Long user_Id) {
        Event event = eventRepository.findById(event_Id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        if (!event.getUser().getId().equals(user_Id)) {
            throw new IllegalArgumentException("User cannot publish this event");
        }
        if (!event.getEventName().getId().equals(user_Id)){
            throw new IllegalArgumentException("debe tener un nombre");
        }
        if(!event.getLocation().getId().equals(user_Id)){
            throw  new IllegalArgumentException("debe tener campo de ubicacion ");

        }


        event.setStatus(com.example.eventgest.domain.enums.EventStatus.PUBLISHED);
        Event updated = eventRepository.save(event);
    return updated;

    }

    @Override
    public Event close(Long event_Id, Long user_Id) {
        Event event = eventRepository.findById(event_Id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        if (!event.getUser().getId().equals(user_Id)) {
            throw new IllegalArgumentException("User cannot close this event");
        }

        if(!event.getEndTime().getId().equals(user_Id)){
            throw new IllegalArgumentException("hora que sale  hora ");
        }

        event.setStatus(com.example.eventgest.domain.enums.EventStatus.CLOSED);
        Event updated = eventRepository.save(event);
        return updated;

    }

    @Override
    public void registerParticipant(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (event.getGuestCount() >= event.getMaxCapacity()) {
            throw new IllegalArgumentException("Event is full");
        }

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUser(user);

        event.getRegistrations().add(registration);
        event.setGuestCount(event.getGuestCount() + 1);

        eventRepository.save(event);


    }
}
