package com.example.eventgest.domain.controller;

import com.example.eventgest.domain.dto.EventTypeDTO;
import com.example.eventgest.domain.service.Impl.EventTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-types")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EventTypeController {

    private final EventTypeService eventTypeService;

    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping
    public ResponseEntity<Iterable<EventTypeDTO>> getAll() {
        return ResponseEntity.ok(eventTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventTypeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventTypeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EventTypeDTO> create(@RequestBody EventTypeDTO dto) {
        return ResponseEntity.ok(eventTypeService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventTypeDTO> update(@PathVariable Long id, @RequestBody EventTypeDTO dto) {
        return ResponseEntity.ok(eventTypeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
