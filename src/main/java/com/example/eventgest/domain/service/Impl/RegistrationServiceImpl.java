package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.ParticipantRepository;
import com.example.eventgest.domain.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationServiceImpl {

    private final RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    // ==========================
    // VALIDACIONES DE USO
    // ==========================
    public boolean isParticipantRegistered(Long participantId) {
        return registrationRepository.existsByParticipant_Id(participantId);
    }

    public boolean isEventWithRegistrations(Long eventId) {
        return registrationRepository.existsByEvent_Id(eventId);
    }
}