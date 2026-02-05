package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.dto.AuditDTO;
import com.example.eventgest.mapper.AuditMapper;
import com.example.eventgest.domain.service.Impl.AuditService;
import com.example.eventgest.persistence.entity.Audit;
import com.example.eventgest.persistence.entity.User;
import com.example.eventgest.domain.repository.AuditRepository;
import com.example.eventgest.domain.repository.UserRepository;
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
    private final AuditMapper auditMapper;

    public AuditServiceImpl(AuditRepository auditRepository,
                            UserRepository userRepository,
                            AuditMapper auditMapper) {
        this.auditRepository = auditRepository;
        this.userRepository = userRepository;
        this.auditMapper = auditMapper;
    }

    // Implementación usando DTO directamente
    @Override
    public AuditDTO saveAudit(AuditDTO auditDTO) {
        User user = userRepository.findById(auditDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Audit audit = auditMapper.toEntity(auditDTO);
        audit.setUser(user);
        audit.setDate(LocalDate.now());
        audit.setTime(LocalTime.now());
        audit.setCreatedAt(LocalDateTime.now());

        auditRepository.save(audit);
        return auditMapper.toDto(audit);
    }

    // Implementación usando action/description/userId
    @Override
    public AuditDTO saveAudit(String action, String description, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Audit audit = new Audit();
        audit.setAction(action);
        audit.setDescription(description);
        audit.setDate(LocalDate.now());
        audit.setTime(LocalTime.now());
        audit.setCreatedAt(LocalDateTime.now());
        audit.setUser(user);

        auditRepository.save(audit);
        return auditMapper.toDto(audit);
    }

    @Override
    public List<AuditDTO> getAllAudits() {
        return auditRepository.findAll()
                .stream()
                .map(auditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditDTO> getAuditsByUser(Long userId) {
        return auditRepository.findByUserId(userId)
                .stream()
                .map(auditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void registerAction(Long userId, String action) {
        saveAudit(action, "Acción registrada automáticamente", userId);
    }

    @Override
    public void log(Long user_Id, String action, String description, Long id) {
        saveAudit(action, description, user_Id);
    }
}

