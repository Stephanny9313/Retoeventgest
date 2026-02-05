package com.example.eventgest.domain.controller;


import com.example.eventgest.domain.dto.AuditDTO;
import com.example.eventgest.domain.service.Impl.AuditService;
import com.example.eventgest.persistence.entity.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResquestMapping ("/api/audits")
public class AuditController {

    @Autowired
    private AuditService auditService;
    private AuditDTO auditDTO;

    @GetMapping("/{Id}")
    public Audit getAuditById(@PathVariable Long id) {
        return auditService.getAuditById(id);
    }
    @PostMapping
    public AuditDTO createAudit(@RequestBody Audit audit) {
        return auditService.saveAudit(auditDTO);
    }

}
