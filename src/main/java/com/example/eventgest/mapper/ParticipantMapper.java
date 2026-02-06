package com.example.eventgest.mapper;

import com.example.eventgest.domain.dto.ParticipantDTO;
import com.example.eventgest.domain.enums.DocumentType;
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
        ParticipantDTO dto = modelMapper.map(participant, ParticipantDTO.class);
        dto.setDocumentType(participant.getDocumentType().name()); // Enum → String
        return dto;
    }

    public Participant toEntity(ParticipantDTO dto) {
        Participant participant = modelMapper.map(dto, Participant.class);
        // String → Enum
        participant.setDocumentType(DocumentType.valueOf(dto.getDocumentType().toUpperCase()));
        return participant;
    }
}
