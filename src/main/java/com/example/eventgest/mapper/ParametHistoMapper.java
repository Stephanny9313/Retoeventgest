package com.example.eventgest.mapper;

import com.example.eventgest.domain.dto.ParametHistoDTO;
import com.example.eventgest.persistence.entity.ParametHistos;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ParametHistoMapper {

    private final ModelMapper modelMapper;

    public ParametHistoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ParametHistoDTO toDto(ParametHistos parametHisto) {
        return modelMapper.map(parametHisto, ParametHistoDTO.class);
    }

    public ParametHistos toEntity(ParametHistoDTO dto) {
        return modelMapper.map(dto, ParametHistos.class);
    }
}
