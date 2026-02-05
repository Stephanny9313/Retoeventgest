package com.example.eventgest.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametHistosRepository extends JpaRepository<ParametHistosRepository, Long> {



        boolean existsByParameterId(Long parameterId);


    }




