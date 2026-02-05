package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.EventType;
import jdk.jfr.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {

    List<Event> findByEventTypeId(Long eventTypeId);

}
