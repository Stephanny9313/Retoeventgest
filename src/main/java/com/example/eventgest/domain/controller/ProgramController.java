package com.example.eventgest.domain.controller;

import com.example.eventgest.domain.dto.ProgramDTO;
import com.example.eventgest.mapper.ProgramMapper;
import com.example.eventgest.domain.service.Impl.ProgramServiceImpl;
import com.example.eventgest.persistence.entity.Program;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/programs")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProgramController {

    private final ProgramServiceImpl programService;
    private final ProgramMapper programMapper;

    public ProgramController(ProgramServiceImpl programService,
                             ProgramMapper programMapper) {
        this.programService = programService;
        this.programMapper = programMapper;
    }

    // ===============================
    // LISTAR TODOS LOS PROGRAMAS
    // ===============================
    @GetMapping
    public ResponseEntity<List<ProgramDTO>> getAllPrograms() {
        List<ProgramDTO> programs = programService
                .programRepository
                .findAll()
                .stream()
                .map(programMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(programs);
    }

    // ===============================
    // OBTENER POR ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<ProgramDTO> getProgramById(@PathVariable Long id) {
        Program program = programService.programRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Programa no encontrado"));
        return ResponseEntity.ok(programMapper.toDto(program));
    }

    // ===============================
    // CREAR PROGRAMA
    // ===============================
    @PostMapping
    public ResponseEntity<ProgramDTO> createProgram(@Valid @RequestBody ProgramDTO dto) {
        Program program = programMapper.toEntity(dto);
        Program saved = programService.programRepository.save(program);
        return ResponseEntity.ok(programMapper.toDto(saved));
    }

    // ===============================
    // ACTUALIZAR PROGRAMA
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<ProgramDTO> updateProgram(@PathVariable Long id,
                                                    @Valid @RequestBody ProgramDTO dto) {
        Program program = programService.programRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Programa no encontrado"));

        // Actualizamos los campos permitidos
        program.setCode(dto.getCode());
        program.setName(dto.getName());

        Program updated = programService.programRepository.save(program);
        return ResponseEntity.ok(programMapper.toDto(updated));
    }

    // ===============================
    // ELIMINAR PROGRAMA
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id); // Ya valida eventos asociados
        return ResponseEntity.noContent().build();
    }
}
