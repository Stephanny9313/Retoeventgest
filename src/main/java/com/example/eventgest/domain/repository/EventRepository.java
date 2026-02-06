
package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository
        extends JpaRepository<Event, Long>,
        JpaSpecificationExecutor<Event> {

    List<Event> findByEventTypeId(Long eventTypeId);

    boolean existsByProgramId(Long programId);

    boolean existsByUserId(Long userId);

    boolean existsByEventTypeId(Long eventTypeId);
}

