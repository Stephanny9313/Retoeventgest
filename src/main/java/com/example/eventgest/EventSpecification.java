package com.example.eventgest;

import com.example.eventgest.persistence.entity.Event;
import com.example.eventgest.domain.enums.EventStatus;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventSpecification {

    public static Specification<Event> filter(
            String status,
            Long programId,
            LocalDate dateFrom,
            LocalDate dateTo
    ) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Estado del evento
            if (status != null && !status.isBlank()) {
                predicates.add(
                        cb.equal(root.get("status"), EventStatus.valueOf(status))
                );
            }

            // Programa
            if (programId != null) {
                predicates.add(
                        cb.equal(root.get("program").get("id"), programId)
                );
            }

            // Fecha inicio desde
            if (dateFrom != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("startAt"), dateFrom.atStartOfDay())
                );
            }

            // Fecha fin hasta
            if (dateTo != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get("endAt"), dateTo.atTime(23, 59, 59))
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}