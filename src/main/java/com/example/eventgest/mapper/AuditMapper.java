package com.example.eventgest.mapper;

import com.example.eventgest.domain.dto.AuditDTO;
import com.example.eventgest.persistence.entity.Audit;
import com.example.eventgest.persistence.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuditMapper {

    public AuditDTO toDto(Audit audit) {
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

    public Audit toEntity(AuditDTO dto, User user) {
        Audit audit = new Audit();
        audit.setAction(dto.getAction());
        audit.setEntity(dto.getEntity());
        audit.setEntityId(dto.getEntityId());
        audit.setDescription(dto.getDescription());
        audit.setDate(dto.getDate());
        audit.setTime(dto.getTime());
        audit.setCreatedAt(LocalDateTime.now());
        audit.setUser(user);
        return audit;
    }

}
