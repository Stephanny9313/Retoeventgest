package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.ParametHistos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametHistosRepository extends JpaRepository<ParametHistos, Long> {

    boolean existsByParameterId(Long parameterId);

}
