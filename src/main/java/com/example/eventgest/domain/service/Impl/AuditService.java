package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.dto.AuditDTO;
import com.example.eventgest.persistence.entity.Audit;

import java.time.LocalDate;
import java.util.List;

public interface AuditService {

    void  log(
            Long user_Id,
            String action,
            String entity,
            Long entityId,
            String description
    );

    List<AuditDTO> findAll();

    List<AuditDTO> findByUser(Long user_Id);

    List<AuditDTO> findByDateRange(LocalDate from, LocalDate to);

}


