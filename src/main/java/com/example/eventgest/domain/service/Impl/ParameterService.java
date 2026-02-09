package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.ParametHistosRepository;
import com.example.eventgest.domain.repository.ParameterRepository;
import com.example.eventgest.domain.repository.UserRepository;
import com.example.eventgest.persistence.entity.ParametHistos;
import com.example.eventgest.persistence.entity.Parameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ParameterService {

    private final ParameterRepository parameterRepository;
    private final ParametHistosRepository parametHistosRepository;
    private final UserRepository userRepository;

    public ParameterService(ParameterRepository parameterRepository,
                            ParametHistosRepository parametHistosRepository, UserRepository userRepository) {
        this.parameterRepository = parameterRepository;
        this.parametHistosRepository = parametHistosRepository;
        this.userRepository = userRepository;
    }

    // Listar todos
    public List<Parameter> getAllParameters() {
        return parameterRepository.findAll();
    }

    // Crear
    public Parameter createParameter(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    // Actualizar
    public Parameter updateParameter(Long id, Parameter dto) {
        Parameter existing = parameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parámetro no encontrado"));
        existing.setClue(dto.getClue());
        existing.setValue(dto.getValue());
        existing.setType(dto.getType());
        existing.setModifiable(dto.getModifiable());
        existing.setActive(dto.getActive());
        return parameterRepository.save(existing);
    }

    // Eliminar (verificando historial)
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

    // Desactivar parámetro
    public Parameter deactivateParameter(Long id) {
        Parameter parameter = parameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parámetro no encontrado"));
        parameter.setActive(false);
        return parameterRepository.save(parameter);
    }

    public void updateParameter(Long parameterId, String newValue, Long userId) {
        Parameter parameter = parameterRepository.findById(parameterId)
                .orElseThrow(() -> new RuntimeException("Parámetro no encontrado"));

        String oldValue = parameter.getValue(); // guardamos el valor anterior
        parameter.setValue(newValue);           // actualizamos el parámetro
        parameterRepository.save(parameter);

        // registramos en historial
        ParametHistos histo = new ParametHistos();
        histo.setParameter(parameter);
        histo.setPreviousValue(oldValue);
        histo.setNewValue(newValue);
        histo.setDate(LocalDate.now());
        histo.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        parametHistosRepository.save(histo);
    }

}
