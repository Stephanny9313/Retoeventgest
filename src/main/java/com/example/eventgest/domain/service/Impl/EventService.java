package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface EventService {

    EventDTO createEvent(EventDTO dto, Long user_Id);

    EventDTO updateEvent(Long id, EventDTO dto, Long user_Id);

    EventDTO publish(Long id, Long user_Id);

    EventDTO close(Long id, Long user_Id);

    EventDTO findById(Long id);

    Page<EventDTO> findFiltered(
            String status,
            Long programId,
            LocalDate dateFrom,
            LocalDate dateTo,
            Pageable pageable
    );
}




