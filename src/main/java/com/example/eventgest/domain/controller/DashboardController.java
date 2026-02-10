package com.example.eventgest.domain.controller;

import com.example.eventgest.EventGestAiService;
import com.example.eventgest.domain.repository.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class DashboardController {

        private final EventRepository eventRepo;
        private final ProgramRepository programRepo;
        private final EventTypeRepository eventTypeRepo;
        private final ParticipantRepository participantRepo;
        private final AuditRepository auditRepo;
        private final ParameterRepository parameterRepo;
        private final ParametHistosRepository parametHistosRepo;
        private final EventGestAiService aiService;

        public DashboardController(EventRepository eventRepo,
                                   ProgramRepository programRepo,
                                   EventTypeRepository eventTypeRepo,
                                   ParticipantRepository participantRepo,
                                   AuditRepository auditRepo,
                                   ParameterRepository parameterRepo,
                                   ParametHistosRepository parametHistosRepo,
                                   EventGestAiService aiService) {
            this.eventRepo = eventRepo;
            this.programRepo = programRepo;
            this.eventTypeRepo = eventTypeRepo;
            this.participantRepo = participantRepo;
            this.auditRepo = auditRepo;
            this.parameterRepo = parameterRepo;
            this.parametHistosRepo = parametHistosRepo;
            this.aiService = aiService;
        }

        @GetMapping
        public Map<String, Object> getDashboard() {
            Map<String, Object> data = new HashMap<>();

            data.put("eventsCount", eventRepo.count());
            data.put("programsCount", programRepo.count());
            data.put("eventTypesCount", eventTypeRepo.count());
            data.put("participantsCount", participantRepo.count());
            data.put("auditsCount", auditRepo.count());
            data.put("parametersCount", parameterRepo.count());
            data.put("paramHistoriesCount", parametHistosRepo.count());

            // Mensaje de bienvenida generado por IA
            data.put("welcomeMessage", aiService.generateGreeting());

            // Últimos 5 eventos
            data.put("recentEvents", eventRepo.findTop5ByOrderByCreatedAtDesc());

            // Últimas auditorías
            data.put("recentAudits", auditRepo.findTop5ByOrderByDateDesc());

            return data;
        }
    }


