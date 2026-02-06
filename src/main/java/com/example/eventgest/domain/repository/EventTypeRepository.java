package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {

    boolean existsById(Long id);
}
