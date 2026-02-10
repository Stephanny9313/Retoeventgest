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
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // ===============================
    // CREAR EVENTO
    // ===============================
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO dto) {
        EventDTO created = eventService.createEvent(dto);
        return ResponseEntity.ok(created);
    }

    // ===============================
    // ACTUALIZAR EVENTO
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id,
                                                @RequestBody EventDTO dto) {
        EventDTO updated = eventService.updateEvent(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ===============================
    // PUBLICAR EVENTO
    // ===============================
    @PatchMapping("/{id}/publish")
    public ResponseEntity<EventDTO> publishEvent(@PathVariable Long id) {
        EventDTO published = eventService.publish(id);
        return ResponseEntity.ok(published);
    }
    // ===============================
    // CERRAR EVENTO
    // ===============================
    @PatchMapping("/{id}/close")
    public ResponseEntity<EventDTO> closeEvent(@PathVariable Long id) {
        EventDTO closed = eventService.close(id);
        return ResponseEntity.ok(closed);
    }
    // ===============================
    // OBTENER EVENTO POR ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        EventDTO dto = eventService.findById(id);
        return ResponseEntity.ok(dto);
    }
    // ===============================
    // FILTRADO DE EVENTOS
    // ===============================
    @GetMapping
    public ResponseEntity<Page<EventDTO>> getEvents(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long programId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            Pageable pageable
    ) {
        Page<EventDTO> page = eventService.findFiltered(status, programId, dateFrom, dateTo, pageable);
        return ResponseEntity.ok(page);
    }


}
