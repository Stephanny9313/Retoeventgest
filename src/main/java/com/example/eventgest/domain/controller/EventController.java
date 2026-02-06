package com.example.eventgest.domain.controller;


import com.example.eventgest.domain.dto.EventDTO;
import com.example.eventgest.domain.service.Impl.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*") // Para Angular / frontend
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // ===============================
    // CREATE
    // ===============================
    @PostMapping
    public ResponseEntity<EventDTO> create(@RequestBody EventDTO dto) {
        return ResponseEntity.ok(eventService.createEvent(dto));
    }

    // ===============================
    // UPDATE
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(
            @PathVariable Long id,
            @RequestBody EventDTO dto
    ) {
        return ResponseEntity.ok(eventService.updateEvent(id, dto));
    }

    // ===============================
    // PUBLISH
    // ===============================
    @PutMapping("/{id}/publish")
    public ResponseEntity<EventDTO> publish(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.publish(id));
    }

    // ===============================
    // CLOSE
    // ===============================
    @PutMapping("/{id}/close")
    public ResponseEntity<EventDTO> close(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.close(id));
    }

    // ===============================
    // FIND BY ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    // ===============================
    // FILTER + PAGINATION
    // ===============================
    @GetMapping
    public ResponseEntity<Page<EventDTO>> findFiltered(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long programId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                eventService.findFiltered(status, programId, dateFrom, dateTo, pageable)
        );
    }
}