package com.example.eventgest.domain.controller;

import com.example.eventgest.domain.dto.RegistrationDTO;
import com.example.eventgest.domain.repository.RegistrationRepository;
import com.example.eventgest.mapper.RegistrationMapper;
import com.example.eventgest.persistence.entity.Event;
import com.example.eventgest.persistence.entity.Participant;
import com.example.eventgest.persistence.entity.Registration;
import com.example.eventgest.domain.service.Impl.RegistrationServiceImpl;
import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.ParticipantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RegistrationController {
    private final RegistrationServiceImpl registrationService;

    public RegistrationController(RegistrationServiceImpl registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDTO>> getAll() {
        return ResponseEntity.ok(registrationService.getAllRegistrations());
    }

    @PostMapping
    public ResponseEntity<RegistrationDTO> register(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(registrationService.registerParticipant(dto));
    }
}


