package com.example.eventgest.domain.controller;

import com.example.eventgest.domain.dto.ParameterDTO;
import com.example.eventgest.persistence.entity.Parameter;
import com.example.eventgest.domain.service.Impl.ParameterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parameters")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ParameterController {

    private final ParameterService parameterService;

    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    // ===============================
    // LISTAR TODOS
    // ===============================
    @GetMapping
    public List<ParameterDTO> getAll() {
        return parameterService.getAllParameters().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ===============================
    // CREAR
    // ===============================
    @PostMapping
    public ParameterDTO create(@RequestBody ParameterDTO dto) {
        Parameter param = toEntity(dto);
        Parameter created = parameterService.createParameter(param);
        return toDTO(created);
    }

    // ===============================
    // ACTUALIZAR
    // ===============================
    @PutMapping("/{id}")
    public ParameterDTO update(@PathVariable Long id, @RequestBody ParameterDTO dto) {
        Parameter param = toEntity(dto);
        Parameter updated = parameterService.updateParameter(id, param);
        return toDTO(updated);
    }
    public void updateParameter(@PathVariable Long id,
                                @RequestParam String newValue,
                                @RequestParam Long userId) {
        parameterService.updateParameter(id, newValue, userId);
    }

    // ===============================
    // ELIMINAR
    // ===============================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        parameterService.deleteParameter(id);
    }

    // ===============================
    // DESACTIVAR
    // ===============================
    @PatchMapping("/{id}/deactivate")
    public ParameterDTO deactivate(@PathVariable Long id) {
        Parameter deactivated = parameterService.deactivateParameter(id);
        return toDTO(deactivated);
    }

    // ===============================
    // CONVERSIÓN DTO <-> ENTITY
    // ===============================
    private ParameterDTO toDTO(Parameter parameter) {
        ParameterDTO dto = new ParameterDTO();
        dto.setId(parameter.getId());
        dto.setClue(parameter.getClue());
        dto.setValue(parameter.getValue());
        dto.setType(parameter.getType());
        dto.setModifiable(parameter.getModifiable());
        return dto;
    }

    private Parameter toEntity(ParameterDTO dto) {
        Parameter param = new Parameter();
        param.setClue(dto.getClue());
        param.setValue(dto.getValue());
        param.setType(dto.getType());
        param.setModifiable(dto.getModifiable());
        return param;
    }
}
