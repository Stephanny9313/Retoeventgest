package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.ParticipantRepository;
import com.example.eventgest.domain.repository.RegistrationRepository;

public class ParticipantServiceImpl {

    private final ParticipantRepository participantRepository;
    private final RegistrationRepository registrationRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, RegistrationRepository registrationRepository) {
        this.participantRepository = participantRepository;
        this.registrationRepository = registrationRepository;
    }
}
