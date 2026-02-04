package com.example.eventgest.mapper;

import com.example.eventgest.domain.dto.ParametHistoDTO;
import com.example.eventgest.persistence.entity.ParametHisto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ParametHistoMapper {

        private final ModelMapper modelMapper;

        public ParametHistoMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public ParametHistoDTO toDto(ParametHisto parametHisto) {
            return modelMapper.map(parametHisto, ParametHistoDTO.class);
        }

        public ParametHisto toEntity(ParametHistoDTO dto) {
            return modelMapper.map(dto, ParametHisto .class);
        }
    }

