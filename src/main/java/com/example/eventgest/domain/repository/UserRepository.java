package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.EventType;
import com.example.eventgest.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);
    Optional<User> findByEmail(String email);

    boolean existsByRol_Id(Long rolId);

}


