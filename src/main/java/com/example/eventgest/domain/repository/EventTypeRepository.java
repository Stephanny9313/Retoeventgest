package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
