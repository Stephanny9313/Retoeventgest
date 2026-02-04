package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.ParametHistoRepository;
import com.example.eventgest.domain.repository.ParameterRepository;
import com.example.eventgest.persistence.entity.Parameter;

public class ParameterServiceImpl {

    private final ParameterRepository parameterRepository;
    private final ParametHistoRepository parametHistoRepository;


    public ParameterServiceImpl(ParameterRepository parameterRepository, ParametHistoRepository parametHistoRepository) {
        this.parameterRepository = parameterRepository;
        this.parametHistoRepository = parametHistoRepository;
    }
}
