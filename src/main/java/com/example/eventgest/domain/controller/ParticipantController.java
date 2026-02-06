package com.example.eventgest.domain.controller;

import com.example.eventgest.domain.dto.ParticipantDTO;
import com.example.eventgest.mapper.ParticipantMapper;
import com.example.eventgest.persistence.entity.Participant;
import com.example.eventgest.domain.service.Impl.ParticipantServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/participants")
@CrossOrigin(origins = "*")
public class ParticipantController {

    private final ParticipantServiceImpl participantService;
    private final ParticipantMapper participantMapper;

    public ParticipantController(ParticipantServiceImpl participantService,
                                 ParticipantMapper participantMapper) {
        this.participantService = participantService;
        this.participantMapper = participantMapper;
    }

    @PostMapping
    public ResponseEntity<ParticipantDTO> create(@Valid @RequestBody ParticipantDTO dto) {
        Participant participant = participantMapper.toEntity(dto);
        Participant saved = participantService.createParticipant(participant);
        return ResponseEntity.ok(participantMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDTO> update(@PathVariable Long id,
                                                 @Valid @RequestBody ParticipantDTO dto) {
        Participant participant = participantMapper.toEntity(dto);
        Participant updated = participantService.updateParticipant(id, participant);
        return ResponseEntity.ok(participantMapper.toDto(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDTO> getById(@PathVariable Long id) {
        Participant participant = participantService.getParticipantById(id);
        return ResponseEntity.ok(participantMapper.toDto(participant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDTO>> getAll() {
        List<ParticipantDTO> list = participantService.getAllParticipants()
                .stream()
                .map(participantMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
