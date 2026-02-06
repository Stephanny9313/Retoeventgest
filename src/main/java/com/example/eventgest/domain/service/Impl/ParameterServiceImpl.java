package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.ParametHistosRepository;
import com.example.eventgest.domain.repository.ParameterRepository;
import com.example.eventgest.persistence.entity.Parameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParameterServiceImpl {

    public final ParameterRepository parameterRepository;
    private final ParametHistosRepository parametHistosRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository,
                                ParametHistosRepository parametHistosRepository) {
        this.parameterRepository = parameterRepository;
        this.parametHistosRepository = parametHistosRepository;
    }

    public void deleteParameter(Long id) {
        Parameter parameter = parameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parámetro no encontrado con id: " + id));

        boolean hasHistory = parametHistosRepository.existsByParameterId(id);

        if (hasHistory) {
            throw new IllegalStateException(
                    "No se puede eliminar el parámetro porque tiene historial asociado"
            );
        }

        parameterRepository.delete(parameter);
    }

    public void deactivateParameter(Long id) {
        Parameter parameter = parameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parámetro no encontrado"));

        parameter.setActive(false);
        parameterRepository.save(parameter);
    }
}
