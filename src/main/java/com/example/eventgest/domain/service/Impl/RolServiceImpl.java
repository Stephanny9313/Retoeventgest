package com.example.eventgest.domain.service.Impl;

import com.example.eventgest.domain.repository.RolRepository;
import com.example.eventgest.domain.repository.UserRepository;
import com.example.eventgest.persistence.entity.Rol;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RolServiceImpl {



        private final RolRepository rolRepository;
        private final UserRepository userRepository;

        public RolServiceImpl(RolRepository rolRepository,
                              UserRepository userRepository) {
            this.rolRepository = rolRepository;
            this.userRepository = userRepository;
        }

        public boolean isRolInUse(Long rolId) {
            return userRepository.existsByRol_Id(rolId); // ✅ CORREGIDO
        }

        public void deleteRol(Long id) {
            if (isRolInUse(id)) {
                throw new IllegalStateException(
                        "No se puede eliminar el rol porque está asignado a usuarios"
                );
            }
            rolRepository.deleteById(id);
        }

    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    public Rol create(Rol rol) {
        return rolRepository.save(rol);
    }
    }


