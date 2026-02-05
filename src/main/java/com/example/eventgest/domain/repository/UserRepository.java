package com.example.eventgest.domain.repository;

import com.example.eventgest.persistence.entity.EventType;
import com.example.eventgest.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByRolId(Long rolId);

    void saveById(Long id);

    void updateById(Long id);

    void assignRol(Long userId, Long rolId);
}
