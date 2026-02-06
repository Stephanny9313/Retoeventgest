package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.EventType;
import com.example.eventgest.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByRolId(Long rolId);

    void saveById(Long id);

    void updateById(Long id);

    void assignRol(Long userId, Long rolId);

    Optional<Object> findByUsername(String username);
}
