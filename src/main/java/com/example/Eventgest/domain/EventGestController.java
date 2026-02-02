package com.example.Eventgest.domain;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventGestController {

    @GetMapping("/hello")

    public String hello() {
        return "Hello, EventGest!";
    }
}
