package com.example.eventgest.domain.controller;


import com.example.eventgest.EventGestAiService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EventGestController {
    private final EventGestAiService aiService;

    public EventGestController(EventGestAiService aiService) {
        this.aiService = aiService;
    }


    @GetMapping("/")

    public String hello() {
        return this.aiService.generateGreeting();
    }
}
