package com.example.eventgest.mapper;


import com.example.eventgest.domain.dto.ParticipantDTO;
import com.example.eventgest.persistence.entity.Participant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {


        private final ModelMapper modelMapper;

        public ParticipantMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public ParticipantDTO toDto(Participant participant) {
            return modelMapper.map(participant,ParticipantDTO.class);
        }

        public Participant toEntity(ParticipantDTO dto) {
            return modelMapper.map(dto, Participant.class);
        }
    }
}
