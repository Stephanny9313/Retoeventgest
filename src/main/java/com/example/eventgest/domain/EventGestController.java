package com.example.eventgest.domain;


import com.example.eventgest.EventGestAiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
