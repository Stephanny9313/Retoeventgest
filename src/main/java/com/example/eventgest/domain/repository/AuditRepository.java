package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.Audit;
import com.example.eventgest.persistence.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditRepository extends JpaRepository<Audit, Long> {
    List<Audit> findByUserId(Long userId);
}
