package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.AuditRepository;
import com.example.eventgest.domain.repository.UserRepository;
import com.example.eventgest.domain.service.dao.Idao;
import com.example.eventgest.persistence.entity.Audit;
import com.example.eventgest.persistence.entity.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
public class AuditServiceImpl  {

    private final AuditRepository auditRepository;
    private final UserRepository userRepository;


    public AuditServiceImpl(AuditRepository auditRepository,
                            UserRepository userRepository) {
        this.auditRepository = auditRepository;
        this.userRepository = userRepository;
    }

    public void registerAction(Long userId, String action, String description, LocalDate date, LocalTime time) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Audit audit = new Audit();
        audit.setUser(user);
        audit.setAction(action);
        audit.setDescription(description);
        audit.setDate(date);
        audit.setTime(time);

        auditRepository.save(audit);
    }
}

