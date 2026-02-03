package com.example.eventgest;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.UserMessage;

@AiService
public interface EventGestAiService {

    @UserMessage("""
            Genera un Saludo de Bienvenida  los usuarios que entrar al software de gestion de eventos EventGest
             utliza menos 12 caracteres al estilo eventos""")

    String generateGreeting();
}
