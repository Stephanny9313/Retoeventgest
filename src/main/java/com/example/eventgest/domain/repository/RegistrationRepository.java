package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.EventType;
import com.example.eventgest.persistence.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByParticipant_Id(Long participantId);
    boolean existsByEvent_Id(Long eventId);
    boolean existsByEvent_IdAndParticipant_Id(Long eventId, Long participantId);

}
