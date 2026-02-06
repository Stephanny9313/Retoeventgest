package com.example.eventgest.domain.controller;

import com.example.eventgest.domain.dto.ParameterDTO;
import com.example.eventgest.domain.repository.ParameterRepository;
import com.example.eventgest.mapper.ParameterMapper;
import com.example.eventgest.persistence.entity.Parameter;
import com.example.eventgest.domain.service.Impl.ParameterServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parameters")
@CrossOrigin(origins = "*") // permite llamadas desde frontend
public class ParameterController {

    private final ParameterServiceImpl parameterService;
    private final ParameterMapper parameterMapper;


    public ParameterController(ParameterServiceImpl parameterService, ParameterMapper parameterMapper) {
        this.parameterService = parameterService;
        this.parameterMapper = parameterMapper;
    }

    // ===============================
    // CREATE
    // ===============================
    @PostMapping
    public ResponseEntity<ParameterDTO> create(@RequestBody ParameterDTO dto) {
        Parameter parameter = parameterMapper.toEntity(dto);
        parameter = parameterService.parameterRepository.save(parameter); // guardar directamente
        return ResponseEntity.ok(parameterMapper.toDto(parameter));
    }

    // ===============================
    // UPDATE
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<ParameterDTO> update(
            @PathVariable Long id,
            @RequestBody ParameterDTO dto
    ) {
        Parameter existing = parameterService.parameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parámetro no encontrado"));

        existing.setClue(dto.getClue());
        existing.setValue(dto.getValue());
        existing.setType(dto.getType());
        existing.setActive(dto.getModifiable()); // si quieres reflejar modifiable en active

        Parameter saved = parameterService.parameterRepository.save(existing);
        return ResponseEntity.ok(parameterMapper.toDto(saved));
    }

    // ===============================
    // DEACTIVATE
    // ===============================
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        parameterService.deactivateParameter(id);
        return ResponseEntity.ok().build();
    }

    // ===============================
    // DELETE
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        parameterService.deleteParameter(id);
        return ResponseEntity.ok().build();
    }

    // ===============================
    // GET BY ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<ParameterDTO> getById(@PathVariable Long id) {
        Parameter parameter = parameterService.parameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parámetro no encontrado"));
        return ResponseEntity.ok(parameterMapper.toDto(parameter));
    }

    // ===============================
    // LIST ALL
    // ===============================
    @GetMapping
    public ResponseEntity<List<ParameterDTO>> getAll() {
        List<ParameterDTO> list = parameterService.parameterRepository.findAll()
                .stream()
                .map(parameterMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
