package com.example.eventgest.domain.service.service;



import com.example.eventgest.domain.dto.EventDTO;
import com.example.eventgest.persistence.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface EventService {
    EventDTO createEvent(EventDTO dto);

    EventDTO updateEvent(Long id, EventDTO dto);

    EventDTO publish(Long id);

    EventDTO close(Long id);

    EventDTO findById(Long id);

    Page<EventDTO> findFiltered(String status, Long programId, LocalDate dateFrom, LocalDate dateTo, Pageable pageable);

    Page<EventDTO> findFiltered(String status, Long programId, LocalDate dateFrom, LocalDate dateTo, org.springframework.data.domain.Pageable pageable);
}

