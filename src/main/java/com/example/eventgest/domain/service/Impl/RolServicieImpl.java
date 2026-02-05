package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.RolRepository;
import com.example.eventgest.domain.repository.UserRepository;

public class RolServicieImpl {

    private final RolRepository rolRepository;
    private final UserRepository userRepository;


    public RolServicieImpl(RolRepository rolRepository, UserRepository userRepository) {
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
    }
        public boolean isRolInUse(Long rolId) {
            return userRepository.existsByRolId(rolId);
        }

        public void deleteRol(Long id) {
            if (isRolInUse(id)) {
                throw new IllegalStateException("Cannot delete rol because it is in use by a user.");
            }
            rolRepository.deleteById(id);
        }

}
