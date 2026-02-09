package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.*;
import com.example.eventgest.persistence.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public  class ProgramServiceImpl {

    public final ProgramRepository programRepository;
    private final EventRepository eventRepository;

    public ProgramServiceImpl(ProgramRepository programRepository,
                              EventRepository eventRepository) {
        this.programRepository = programRepository;
        this.eventRepository = eventRepository;
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