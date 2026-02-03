package com.example.eventgest;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.UserMessage;

@AiService
public interface EventGestAiService {

    @UserMessage("""
            Genera un Saludo de Bienvenida  los usuarios del software de gestion de eventos EventGest
             utlizando menos 120 caracteres al estilo eventos""")

    String generateGreeting();
}
