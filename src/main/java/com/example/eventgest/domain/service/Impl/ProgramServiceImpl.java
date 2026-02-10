package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.dto.ProgramDTO;
import com.example.eventgest.domain.repository.*;
import com.example.eventgest.mapper.ProgramMapper;
import com.example.eventgest.persistence.entity.Program;
import com.example.eventgest.persistence.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProgramServiceImpl {

    public final ProgramRepository programRepository;
    private final EventRepository eventRepository;
    private final ProgramMapper programMapper;

    public ProgramServiceImpl(ProgramRepository programRepository,
                              EventRepository eventRepository,
                              ProgramMapper programMapper) {
        this.programRepository = programRepository;
        this.eventRepository = eventRepository;
        this.programMapper = programMapper;
    }

    public List<ProgramDTO> getAllPrograms() {
        return programRepository.findAll()
                .stream()
                .map(programMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProgramDTO getProgramById(Long id) {
        return programRepository.findById(id)
                .map(programMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Programa no encontrado"));
    }

    public Program saveProgram(Program program) {
        return programRepository.save(program);
    }

    public boolean isProgramInUse(Long programId) {
        return eventRepository.existsByProgram_Id(programId);
    }

    public void deleteProgram(Long id) {
        if (isProgramInUse(id)) {
            throw new IllegalStateException(
                    "No se puede eliminar el programa porque tiene eventos asociados"
            );
        }
        programRepository.deleteById(id);
    }
}