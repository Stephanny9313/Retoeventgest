package com.example.eventgest.domain.service.service;

import com.example.eventgest.domain.dto.AuditDTO;

import java.util.List;

public interface AuditService {
    AuditDTO saveAudit(AuditDTO auditDTO);

    List<AuditDTO> getAllAudits();

    void registerAction(Long userId, String action);

    List<AuditDTO> getAuditsByUser(Long userId);

    void log(Long user_Id, String action, String description, Long id);

    AuditDTO saveAudit(String action, String description, Long userId);
}

