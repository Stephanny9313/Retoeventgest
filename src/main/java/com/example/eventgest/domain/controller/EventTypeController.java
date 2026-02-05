package com.example.eventgest.domain.controller;


import com.example.eventgest.domain.dto.EventTypeDTO;
import com.example.eventgest.domain.service.Impl.EventTypeService;
import com.example.eventgest.persistence.entity.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/EventType")
public class EventTypeController {

    @Autowired
    private EventTypeService eventTypeService;
    private EventTypeDTO eventTypeDTO;

    @GetMapping("/{Id}")
    public EventType getEventTypeById(@PathVariable Long id) {
        return eventTypeService.getEventTypeById(id);
    }

    @PostMapping("/delete/{id}")
    public void deleteEventType(@PathVariable Long id) {
        eventTypeService.deleteEventType(id);

    }


}
