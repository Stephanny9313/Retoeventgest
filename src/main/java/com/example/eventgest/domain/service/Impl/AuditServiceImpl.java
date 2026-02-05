package com.example.eventgest.domain.service.Impl;



import com.example.eventgest.domain.dto.AuditDTO;
import com.example.eventgest.mapper.AuditMapper;
import com.example.eventgest.domain.service.service.AuditService;
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

    public AuditServiceImpl(AuditRepository auditRepository,
                            UserRepository userRepository) {
        this.auditRepository = auditRepository;
        this.userRepository = userRepository;
    }

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

        // Si tienes AuditMapper
        return null; // o auditMapper.toDto(audit);
    }
}