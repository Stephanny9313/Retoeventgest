package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.*;
import com.example.eventgest.persistence.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuditRepository auditRepository;
    private final EventRepository eventRepository;
    private final RolRepository rolRepository;

    public UserServiceImpl(UserRepository userRepository,
                           AuditRepository auditRepository,
                           EventRepository eventRepository,
                           RolRepository rolRepository) {
        this.userRepository = userRepository;
        this.auditRepository = auditRepository;
        this.eventRepository = eventRepository;
        this.rolRepository = rolRepository;
    }

    // ===============================
    // CONSULTAS
    // ===============================
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }


    // ===============================
    // CREAR / ACTUALIZAR
    // ===============================

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }


    @Override
    public User update(User user) {
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            throw new IllegalStateException("No se puede actualizar un usuario inexistente");
        }
        return userRepository.save(user);
    }

    // ===============================
    // BORRADO CON REGLAS DE NEGOCIO
    // ===============================

    @Override
    public void delete(Long userId) {
        if (isUserInUse(userId)) {
            throw new IllegalStateException(
                    "No se puede eliminar el usuario porque tiene información asociada"
            );
        }
        userRepository.deleteById(userId);
    }

    private boolean isUserInUse(Long userId) {
        return auditRepository.existsByUser_Id(userId)
                || eventRepository.existsByOwner_Id(userId);
    }

    // ===============================
    // ROLES
    // ===============================

    @Override
    public boolean isRolInUse(Long rolId) {
        return userRepository.existsByRol_Id(rolId);
    }

    public void assignRol(Long userId, Long rolId) {
        User user = getById(userId);
        user.setRol(
                rolRepository.findById(rolId)
                        .orElseThrow(() -> new IllegalStateException("Rol no encontrado"))
        );
        userRepository.save(user);
    }
}

