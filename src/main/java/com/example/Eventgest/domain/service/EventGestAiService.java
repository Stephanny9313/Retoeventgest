package com.example.Eventgest.domain.service;


import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface EventGestAiService {

    @UserMessage("Generate a greeting message for the given name.")
    String generateGreeting(String name);

}
