package com.example.eventgest.mapper;

import com.example.eventgest.domain.dto.AuditDTO;
import com.example.eventgest.persistence.entity.Audit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuditMapper {



        private final ModelMapper modelMapper;

        public AuditMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public AuditDTO toDto(Audit audit) {
            return modelMapper.map(audit, AuditDTO.class);
        }

        public Audit toEntity(AuditDTO dto) {
            return modelMapper.map(dto, Audit.class);
        }
    }

