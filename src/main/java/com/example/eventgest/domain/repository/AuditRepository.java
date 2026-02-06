package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.Audit;
import com.example.eventgest.persistence.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface AuditRepository extends JpaRepository<Audit, Long> {
    // Buscar auditorías de un usuario
    List<Audit> findByUser_Id(Long userId);

    // Comprobar si existen auditorías para un usuario
    boolean existsByUser_Id(Long userId);

    // Buscar auditorías por rango de fechas
    List<Audit> findByDateBetween(LocalDate from, LocalDate to);
}