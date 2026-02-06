package com.example.eventgest.domain.controller;


import com.example.eventgest.domain.dto.AuditDTO;
import com.example.eventgest.domain.service.Impl.AuditService;
import com.example.eventgest.persistence.entity.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/audits")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    // ======================================================
    // LISTAR TODA LA AUDITORÍA (ADMIN)
    // ======================================================
    @GetMapping
    public ResponseEntity<List<AuditDTO>> getAllAudits() {
        return ResponseEntity.ok(auditService.findAll());
    }

    // ======================================================
    // FILTRAR POR USUARIO
    // ======================================================
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AuditDTO>> getAuditsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(auditService.findByUser(userId));
    }




    @GetMapping("/date-range")
    public ResponseEntity<List<AuditDTO>> getAuditsByDateRange(
            @RequestParam("from")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,

            @RequestParam("to")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return ResponseEntity.ok(auditService.findByDateRange(from, to));
    }
}

