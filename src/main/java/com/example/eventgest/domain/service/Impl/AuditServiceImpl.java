package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.dto.AuditDTO;
import com.example.eventgest.domain.repository.AuditRepository;
import com.example.eventgest.domain.repository.UserRepository;
import com.example.eventgest.domain.service.Impl.AuditService;
import com.example.eventgest.persistence.entity.Audit;
import com.example.eventgest.persistence.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;
    private final UserRepository userRepository;

    public AuditServiceImpl(AuditRepository auditRepository,
                            UserRepository userRepository) {
        this.auditRepository = auditRepository;
        this.userRepository = userRepository;
    }

    // ======================================================
    // REGISTRO CENTRAL DE AUDITORÍA
    // ======================================================
    @Override
    public void log(Long userId,
                    String action,
                    String entity,
                    Long entityId,
                    String description) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Audit audit = new Audit();
        audit.setAction(action);
        audit.setEntity(entity);
        audit.setEntityId(entityId);
        audit.setDescription(description);
        audit.setDate(LocalDate.now());
        audit.setTime(LocalTime.now());
        audit.setCreatedAt(LocalDateTime.now());
        audit.setUser(user);

        auditRepository.save(audit);
    }

    // ======================================================
    // CONSULTAS
    // ======================================================
    @Override
    @Transactional(readOnly = true)
    public List<AuditDTO> findAll() {
        return auditRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditDTO> findByUser(Long userId) {
        return auditRepository.findByUserId(userId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditDTO> findByDateRange(LocalDate from, LocalDate to) {
        return auditRepository.findByDateBetween(from, to)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ======================================================
    // MAPPER PRIVADO (CONTROLADO)
    // ======================================================
    private AuditDTO toDto(Audit audit) {
        AuditDTO dto = new AuditDTO();
        dto.setId(audit.getId());
        dto.setAction(audit.getAction());
        dto.setEntity(audit.getEntity());
        dto.setEntityId(audit.getEntityId());
        dto.setDescription(audit.getDescription());
        dto.setDate(audit.getDate());
        dto.setTime(audit.getTime());
        dto.setUserId(audit.getUser().getId());
        return dto;
    }

}

