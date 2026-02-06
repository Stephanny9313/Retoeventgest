package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.persistence.entity.Participant;
import com.example.eventgest.domain.repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ParticipantServiceImpl {

    private final ParticipantRepository participantRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant updateParticipant(Long id, Participant participant) {
        Participant existing = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado"));

        existing.setName(participant.getName());
        existing.setEmail(participant.getEmail());
        existing.setPhone(participant.getPhone());
        existing.setDocumentType(participant.getDocumentType());
        existing.setDocumentNumber(participant.getDocumentNumber());

        return participantRepository.save(existing);
    }

    public Participant getParticipantById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado"));
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }
}
