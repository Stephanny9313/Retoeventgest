package com.example.eventgest;

import com.example.eventgest.domain.enums.EventStatus;
import com.example.eventgest.persistence.entity.Event;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


import java.time.LocalDate;

public class EventSpecification {

    public static Specification<Event> filter(String status, Long programId, LocalDate dateFrom, LocalDate dateTo) {
        return (root, query, cb) -> {

            Predicate predicate = cb.conjunction();

            // Filtrar por estado
            if (status != null && !status.isEmpty()) {
                try {
                    EventStatus eventStatus = EventStatus.valueOf(status.toUpperCase());
                    predicate = cb.and(predicate, cb.equal(root.get("status").as(EventStatus.class), eventStatus));
                } catch (IllegalArgumentException e) {
                    // Ignorar si el estado es inválido
                }
            }

            // Filtrar por programa
            if (programId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("program").get("id"), programId));
            }

            // Filtrar por fecha desde
            if (dateFrom != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("date").as(LocalDate.class), dateFrom));
            }

            // Filtrar por fecha hasta
            if (dateTo != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("date").as(LocalDate.class), dateTo));
            }

            return predicate;
        };
    }
}