package com.example.eventgest.mapper;


import com.example.eventgest.domain.dto.RegistrationDTO;
import com.example.eventgest.persistence.entity.Registration;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {



        private final ModelMapper modelMapper;

        public RegistrationMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public RegistrationDTO toDto(Registration registration) {
            return modelMapper.map(registration, RegistrationDTO.class);
        }

        public Registration toEntity(RegistrationDTO dto) {
            return modelMapper.map(dto, Registration.class);
        }
    }


