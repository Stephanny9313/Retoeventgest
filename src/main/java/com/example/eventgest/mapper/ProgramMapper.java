package com.example.eventgest.mapper;

import com.example.eventgest.domain.dto.ProgramDTO;
import com.example.eventgest.persistence.entity.Program;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProgramMapper {


        private final ModelMapper modelMapper;

        public ProgramMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public ProgramDTO toDto(Program program) {
            return modelMapper.map(program, ProgramDTO.class);
        }

        public Program toEntity(ProgramDTO dto) {
            return modelMapper.map(dto, Program.class);
        }
    }

