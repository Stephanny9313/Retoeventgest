package com.example.eventgest.domain.service.Impl;



import com.example.eventgest.persistence.entity.Event;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    Event getEventById(Long id);

    Event createEvent(Event event);

    Event updateEvent(Long id, Event eventDetails);

    @Transactional
    void deleteEvent(Long id);

    Event create(Event event, Long userId);
    Event update(Long eventId, Event event);
    void delete(Long eventId);
    Event publish(Long eventId, Long userId);
    Event close(Long eventId, Long userId);
    Event findById(Long id);
    List<Event> findAll();
    void registerParticipant(Long eventId, Long userId);

}