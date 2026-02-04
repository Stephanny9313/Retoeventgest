package com.example.eventgest.mapper;

import com.example.eventgest.domain.dto.UserDTO;
import com.example.eventgest.persistence.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


        private final ModelMapper modelMapper;

        public UserMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public UserDTO toDto(User user) {
            return modelMapper.map(user, UserDTO.class);
        }

        public User toEntity(UserDTO dto) {
            return modelMapper.map(dto, UserDTO.class);
        }
    }
}
