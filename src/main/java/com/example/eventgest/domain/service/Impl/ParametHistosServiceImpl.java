package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.ParametHistosRepository;
import com.example.eventgest.domain.repository.ParameterRepository;
import com.example.eventgest.domain.repository.UserRepository;
import com.example.eventgest.persistence.entity.Event;
import com.example.eventgest.persistence.entity.ParametHistos;
import com.example.eventgest.persistence.entity.Parameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class ParametHistosServiceImpl {

    private final ParameterRepository parameterRepository;
    public final ParametHistosRepository parametHistosRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public ParametHistosServiceImpl(
            ParameterRepository parameterRepository,
            ParametHistosRepository parametHistosRepository,
            EventRepository eventRepository, UserRepository userRepository) {
        this.parameterRepository = parameterRepository;
        this.parametHistosRepository = parametHistosRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    // registrar cambio de parámetro
    public void registerParameterChange(Long parameterId, String oldValue, String newValue, Long userId) {
        Parameter parameter = parameterRepository.findById(parameterId)
                .orElseThrow(() -> new RuntimeException("Parámetro no encontrado"));

        ParametHistos histo = new ParametHistos();
        histo.setParameter(parameter);
        histo.setPreviousValue(oldValue);
        histo.setNewValue(newValue);
        histo.setDate(LocalDate.now());
        // histo.setUser(userRepository.findById(userId).orElseThrow(...)); // si quieres guardar usuario

        parametHistosRepository.save(histo);
    }

    // registrar uso de parámetro en evento
    public void registerParameterUsage(Long parameterId, Long eventId) {
        Parameter parameter = parameterRepository.findById(parameterId)
                .orElseThrow(() -> new RuntimeException("Parámetro no encontrado"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        ParametHistos histo = new ParametHistos();
        histo.setParameter(parameter);
        histo.setUser(event.getOwner()); // o el campo que tengas en Event
        histo.setDate(LocalDate.now());

        parametHistosRepository.save(histo);
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
