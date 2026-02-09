package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.dto.RegistrationDTO;
import com.example.eventgest.domain.enums.AttendanceStatus;
import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.ParticipantRepository;
import com.example.eventgest.domain.repository.RegistrationRepository;
import com.example.eventgest.mapper.RegistrationMapper;
import com.example.eventgest.persistence.entity.Event;
import com.example.eventgest.persistence.entity.Participant;
import com.example.eventgest.persistence.entity.Registration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RegistrationServiceImpl {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private final RegistrationMapper registrationMapper;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository,
                                   EventRepository eventRepository,
                                   ParticipantRepository participantRepository,
                                   RegistrationMapper registrationMapper) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
        this.registrationMapper = registrationMapper;
    }

    // Registrar participante
    public RegistrationDTO registerParticipant(RegistrationDTO dto) {
        Event event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        Participant participant = participantRepository.findById(dto.getParticipantId())
                .orElseThrow(() -> new RuntimeException("Participante no encontrado"));

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setParticipant(participant);
        registration.setRegistrationDate(LocalDate.now());
        registration.setAttendance(AttendanceStatus.PENDING);

        Registration saved = registrationRepository.save(registration);
        return registrationMapper.toDto(saved);
    }

    // Listar registros
    public List<RegistrationDTO> getAllRegistrations() {
        return registrationRepository.findAll().stream()
                .map(registrationMapper::toDto)
                .collect(Collectors.toList());
    }
}