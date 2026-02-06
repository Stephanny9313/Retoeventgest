package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.dto.AuditDTO;
import com.example.eventgest.persistence.entity.Audit;

import java.time.LocalDate;
import java.util.List;

public interface AuditService {



    void log(Long userId, String action, String entity, Long entityId, String description);

    List<AuditDTO> findAll();

    List<AuditDTO> findByUser(Long userId);

    List<AuditDTO> findByDateRange(LocalDate from, LocalDate to);

    void registerAction(Long userId, String action, String description, Long entityId);




}


