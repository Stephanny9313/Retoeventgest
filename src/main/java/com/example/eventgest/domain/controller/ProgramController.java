package com.example.eventgest.domain.controller;

import com.example.eventgest.domain.dto.ProgramDTO;
import com.example.eventgest.mapper.ProgramMapper;
import com.example.eventgest.domain.service.Impl.ProgramServiceImpl;
import com.example.eventgest.persistence.entity.Program;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // GENERAR CÓDIGO SECUENCIAL
    // ===============================
    private String generateCode() {
        List<Program> allPrograms = programService.programRepository.findAll();

        if (allPrograms.isEmpty()) {
            return "PROG001";
        }

        // Obtener el código más alto
        int maxNumber = allPrograms.stream()
                .map(p -> {
                    String num = p.getCode().replace("PROG", "");
                    try {
                        return Integer.parseInt(num);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .max(Integer::compare)
                .orElse(0);

        return "PROG" + String.format("%03d", maxNumber + 1);
    }

    // ===============================
    // LISTAR TODOS LOS PROGRAMAS
    // ===============================
    @GetMapping
    public ResponseEntity<List<ProgramDTO>> getAllPrograms() {
        List<ProgramDTO> programs = programService.getAllPrograms();
        return ResponseEntity.ok(programs);
    }

    // ===============================
    // OBTENER POR ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<ProgramDTO> getProgramById(@PathVariable Long id) {
        ProgramDTO dto = programService.getProgramById(id);
        return ResponseEntity.ok(dto);
    }

    // ===============================
    // CREAR PROGRAMA (con código auto)
    // ===============================
    @PostMapping
    public ResponseEntity<ProgramDTO> createProgram(@Valid @RequestBody ProgramDTO dto) {
        Program program = programMapper.toEntity(dto);

        // Si no tiene código, generar uno automáticamente
        if (program.getCode() == null || program.getCode().isEmpty()) {
            program.setCode(generateCode());
        }

        Program saved = programService.saveProgram(program);
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

        program.setName(dto.getName());
        program.setStarYear(dto.getStarYear());
        program.setEndYear(dto.getEndYear());
        program.setStatus(dto.getStatus());
        // No cambiar el código

        Program updated = programService.saveProgram(program);
        return ResponseEntity.ok(programMapper.toDto(updated));
    }

    // ===============================
    // ELIMINAR PROGRAMA
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
}
