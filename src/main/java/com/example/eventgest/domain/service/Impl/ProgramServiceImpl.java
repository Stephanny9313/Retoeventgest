package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.ProgramRepository;

public class ProgramServiceImpl {

    private final ProgramRepository programRepository;
    private final EventRepository eventRepository;


    public ProgramServiceImpl(ProgramRepository programRepository, EventRepository eventRepository) {
        this.programRepository = programRepository;
        this.eventRepository = eventRepository;
    }

        public boolean isProgramInUse(Long programId) {
            return eventRepository.existsByProgramId(programId);
        }

        public void deleteProgram(Long id) {
            if (isProgramInUse(id)) {
                throw new IllegalStateException("Cannot delete program because it is in use by an event.");
            }
            programRepository.deleteById(id);
        }





}
