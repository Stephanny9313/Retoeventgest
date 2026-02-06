package com.example.eventgest.domain.controller;


import com.example.eventgest.domain.dto.ParametHistoDTO;
import com.example.eventgest.domain.repository.ParametHistosRepository;
import com.example.eventgest.domain.service.Impl.ParametHistosServiceImpl;
import com.example.eventgest.mapper.ParametHistoMapper;
import com.example.eventgest.persistence.entity.ParametHistos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/paramet-histos")
@CrossOrigin(origins = "*")
public class ParametHistosController {

    private final ParametHistosServiceImpl histosService;
    private final ParametHistoMapper histoMapper;
    private final ParametHistosRepository parametHistosRepository;

    public ParametHistosController(ParametHistosServiceImpl histosService, ParametHistoMapper histoMapper, ParametHistosRepository parametHistosRepository) {
        this.histosService = histosService;
        this.histoMapper = histoMapper;
        this.parametHistosRepository = parametHistosRepository;
    }

    // ===============================
    // REGISTRAR CAMBIO DE PARAMETRO
    // ===============================
    @PostMapping("/register-change")
    public ResponseEntity<Void> registerChange(
            @RequestParam Long parameterId,
            @RequestParam String oldValue,
            @RequestParam String newValue,
            @RequestParam Long userId
    ) {
        histosService.registerParameterChange(parameterId, oldValue, newValue, userId);
        return ResponseEntity.ok().build();
    }

    // ===============================
    // REGISTRAR USO DE PARAMETRO EN EVENTO
    // ===============================
    @PostMapping("/register-usage")
    public ResponseEntity<Void> registerUsage(
            @RequestParam Long parameterId,
            @RequestParam Long eventId
    ) {
        histosService.registerParameterUsage(parameterId, eventId);
        return ResponseEntity.ok().build();
    }

    // ===============================
    // LISTAR TODOS LOS HISTORIALES
    // ===============================
    @GetMapping
    public ResponseEntity<List<ParametHistoDTO>> getAll() {
        List<ParametHistoDTO> list = histosService.parametHistosRepository.findAll()
                .stream()
                .map(histoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // ===============================
    // OBTENER HISTORIAL POR ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<ParametHistoDTO> getById(@PathVariable Long id) {
        ParametHistos histo = histosService.parametHistosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));
        return ResponseEntity.ok(histoMapper.toDto(histo));
    }
}