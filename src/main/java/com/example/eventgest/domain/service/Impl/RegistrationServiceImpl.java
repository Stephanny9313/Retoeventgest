package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.ParticipantRepository;
import com.example.eventgest.domain.repository.RegistrationRepository;

public class RegistrationServiceImpl {

    private final ParticipantRepository  participantRepository;
    private final RegistrationRepository registrationRepository;
   private final EventRepository eventRepository;

    public RegistrationServiceImpl(ParticipantRepository participantRepository, RegistrationRepository registrationRepository, EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
    }

    public boolean isParticipantInUse(Long participantId) {
        return registrationRepository.existsByParticipantId(participantId);
    }

    public void deleteParticipant(Long id) {
        if (isParticipantInUse(id)) {
            throw new IllegalStateException("Cannot delete participant because they are registered for an event.");
        }
        participantRepository.deleteById(id);
    }

     public boolean isEventInUse(Long eventId) {
        return registrationRepository.existsByEventId(eventId);
    }

        public void deleteEvent(Long id) {
            if (isEventInUse(id)) {
                throw new IllegalStateException("Cannot delete event because it has registered participants.");
            }
            eventRepository.deleteById(id);
        }








}
