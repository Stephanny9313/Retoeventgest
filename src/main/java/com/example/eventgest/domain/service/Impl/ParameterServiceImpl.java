package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.ParametHistosRepository;
import com.example.eventgest.domain.repository.ParameterRepository;
import com.example.eventgest.persistence.entity.Parameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ParameterServiceImpl {

    private final ParameterRepository parameterRepository;
    private final ParametHistosRepository parametHistoRepository;

    public ParameterServiceImpl(
            ParameterRepository parameterRepository,
            ParametHistosRepository parametHistoRepository) {
        this.parameterRepository = parameterRepository;
        this.parametHistoRepository = parametHistoRepository;
    }

    public void deleteParameter(Long id) {

        Parameter parameter = parameterRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Parámetro no encontrado con id: " + id)
                );

        boolean hasHistory =
                parametHistoRepository.existsByParameterId(id);

        if (hasHistory) {
            throw new IllegalStateException(
                    "No se puede eliminar el parámetro porque tiene historial asociado"
            );
        }

        parameterRepository.delete(parameter);
    }

    // ✔ alternativa recomendada
    public void deactivateParameter(Long id) {
        Parameter parameter = parameterRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Parámetro no encontrado")
                );

        parameter.setActive(false);
        parameterRepository.save(parameter);
    }
}







