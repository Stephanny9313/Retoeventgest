package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.AuditRepository;
import com.example.eventgest.domain.repository.EventRepository;
import com.example.eventgest.domain.repository.RolRepository;
import com.example.eventgest.domain.repository.UserRepository;

public class UserServiceImpl {

    private final UserRepository userRepository;
    private final AuditRepository auditRepository;
    private final RolRepository rolRepository;
    private final EventRepository eventRepository;


    public UserServiceImpl(UserRepository userRepository, AuditRepository auditRepository, RolRepository rolRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.auditRepository = auditRepository;
        this.rolRepository = rolRepository;
        this.eventRepository = eventRepository;
    }
        public boolean isUserInUse(Long userId) {
            return auditRepository.existsByUserId(userId) || eventRepository.existsByOrganizerId(userId);
        }

        public void createUser(Long id) {
            if (isUserInUse(id)) {
                throw new IllegalStateException("no se puede crear el usuario porque ya está registrado.");
            }
            userRepository.saveById(id);
        }

        public void updateUser(Long id) {
            if (!userRepository.existsById(id)) {
                throw new IllegalStateException("no puede actualizar el usuario porque no existe.");
            }
            userRepository.updateById(id);
        }
        public boolean isRolAssignedToUser(Long rolId) {
            return userRepository.existsByRolId(rolId);
        }



        public void deleteUser(Long id) {
            if (isUserInUse(id)) {
                throw new IllegalStateException("no puede eliminar el usuario ");
            }
            userRepository.deleteById(id);
        }
        public void assignRolToUser(Long userId, Long rolId) {
            if (!rolRepository.existsById(rolId)) {
                throw new IllegalStateException("no puede asignar rol al usuario porque el usuario no existe.");
            }
            userRepository.assignRol(userId, rolId);
        }




}
