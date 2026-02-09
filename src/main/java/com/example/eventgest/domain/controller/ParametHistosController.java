package com.example.eventgest.domain.controller;

import com.example.eventgest.domain.dto.ParametHistoDTO;
import com.example.eventgest.domain.service.Impl.ParametHistosServiceImpl;
import com.example.eventgest.persistence.entity.ParametHistos;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/paramethistos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ParametHistosController {

    private final ParametHistosServiceImpl service;

    public ParametHistosController(ParametHistosServiceImpl service) {
        this.service = service;
    }

    // ===============================
    // LISTAR HISTORIAL DE UN PARAMETRO
    // ===============================
    @GetMapping
    public List<ParametHistoDTO> getHistories(@RequestParam Long parameterId) {
        List<ParametHistos> histories = service.parametHistosRepository.findAll()
                .stream()
                .filter(h -> h.getParameter().getId().equals(parameterId))
                .collect(Collectors.toList());

        return histories.stream()
                .map(h -> {
                    ParametHistoDTO dto = new ParametHistoDTO();
                    dto.setId(h.getId());
                    dto.setPreviousValue(h.getPreviousValue());
                    dto.setNewValue(h.getNewValue());
                    dto.setDate(h.getDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // ===============================
    // REGISTRAR CAMBIO MANUAL DE PARAMETRO (OPCIONAL)
    // ===============================
    @PostMapping
    public void registerChange(@RequestParam Long parameterId,
                               @RequestParam Long userId,
                               @RequestParam String oldValue,
                               @RequestParam String newValue) {
        service.registerParameterChange(parameterId, oldValue, newValue, userId);
    }
}
