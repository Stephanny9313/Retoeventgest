package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.ParticipantRepository;
import com.example.eventgest.domain.repository.RegistrationRepository;
import com.example.eventgest.persistence.entity.Participant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParticipantServiceImpl {

    private final ParticipantRepository participantRepository;
    private final RegistrationRepository registrationRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, RegistrationRepository registrationRepository) {
        this.participantRepository = participantRepository;
        this.registrationRepository = registrationRepository;
    }


    public void deleteParticipant(Long id) {
        if (isParticipantInUse(id)) {
            throw new IllegalStateException("No se puede eliminar el participante porque está en uso.");
        }
        participantRepository.deleteById(id);
    }


    public boolean isParticipantInUse(Long participantId) {
        return registrationRepository.existsByParticipantId(participantId);
    }



    public Participant updateParticipant(Long id, Participant participant) {
        Participant existingParticipant = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado con id: " + id));

        existingParticipant.setName(participant.getName());
        existingParticipant.setEmail(participant.getEmail());
        existingParticipant.setPhone(participant.getPhone());
        // Actualizar otros campos según sea necesario

        return participantRepository.save(existingParticipant);
    }

}
