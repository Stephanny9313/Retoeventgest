package com.example.eventgest.mapper;

import com.example.eventgest.domain.dto.ParameterDTO;
import com.example.eventgest.persistence.entity.Parameter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ParameterMapper {


        private final ModelMapper modelMapper;

        public ParameterMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public ParameterDTO toDto(Parameter parameter) {
            return modelMapper.map(parameter, ParameterDTO.class);
        }

        public Parameter toEntity(ParameterDTO dto) {
            return modelMapper.map(dto, Parameter.class);
        }


    }


