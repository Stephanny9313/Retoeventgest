package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.EventType;
import com.example.eventgest.persistence.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
}
