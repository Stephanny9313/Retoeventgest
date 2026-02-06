package com.example.eventgest.domain.controller;

import com.example.eventgest.domain.dto.EventTypeDTO;
import com.example.eventgest.domain.service.Impl.EventTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-types")
public class EventTypeController {

    private final EventTypeService eventTypeService;

    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    // 🔍 Obtener tipo de evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<EventTypeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventTypeService.getById(id));
    }

    // 🗑️ Eliminar tipo de evento (con validación de uso)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventTypeService.deleteEventType(id);
        return ResponseEntity.noContent().build();
    }

    // ❗ Manejo de errores controlado
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
