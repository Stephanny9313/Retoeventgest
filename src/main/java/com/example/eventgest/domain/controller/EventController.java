package com.example.eventgest.domain.controller;


import com.example.eventgest.domain.dto.EventDTO;
import com.example.eventgest.domain.service.Impl.AuditService;
import com.example.eventgest.domain.service.Impl.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;



@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:4200") // AngularJS en dev
public class EventController {

    private final EventService eventService;
    private final AuditService auditService;

    public EventController(EventService eventService, AuditService auditService) {
        this.eventService = eventService;
        this.auditService = auditService;
    }

    @GetMapping
    public Page<EventDTO> list(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long programId,
            @RequestParam(required = false) LocalDate dateFrom,
            @RequestParam(required = false) LocalDate dateTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return eventService.findFiltered(status, programId, dateFrom, dateTo, PageRequest.of(page, size));
    }


    @GetMapping("/{id}")
    public EventDTO get(@PathVariable Long id) {
        return eventService.findById(id);
    }


    @PostMapping
    public EventDTO create(@RequestBody EventDTO dto, @RequestParam Long userId) {
        EventDTO created = eventService.createEvent(dto);
        auditService.saveAudit("CREATE_EVENT", "Se creó el evento: " + created.getEventName(), userId);
        return created;
    }


    @PutMapping("/{id}")
    public EventDTO update(@PathVariable Long id, @RequestBody EventDTO dto, @RequestParam Long userId) {
        EventDTO updated = eventService.updateEvent(id, dto);
        auditService.saveAudit("UPDATE_EVENT", "Se actualizó el evento: " + updated.getEventName(), userId);
        return updated;
    }


    @PatchMapping("/{id}/publish")
    public EventDTO publish(@PathVariable Long id, @RequestParam Long userId) {
        EventDTO published = eventService.publish(id);
        auditService.saveAudit("PUBLISH_EVENT", "Se publicó el evento: " + published.getEventName(), userId);
        return published;
    }


    @PatchMapping("/{id}/close")
    public EventDTO close(@PathVariable Long id, @RequestParam Long userId) {
        EventDTO closed = eventService.close(id);
        auditService.saveAudit("CLOSE_EVENT", "Se cerró el evento: " + closed.getEventName(), userId);
        return closed;
    }
}