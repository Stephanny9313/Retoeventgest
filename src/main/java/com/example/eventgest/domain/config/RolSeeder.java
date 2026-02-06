package com.example.eventgest.domain.config;

import com.example.eventgest.domain.enums.Roltype;
import com.example.eventgest.domain.enums.UserStatus;
import com.example.eventgest.domain.service.Impl.RolServiceImpl;
import com.example.eventgest.persistence.entity.Rol;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class RolSeeder implements CommandLineRunner {



        private final RolServiceImpl rolService;

        public RolSeeder(RolServiceImpl rolService) {
            this.rolService = rolService;
        }

        @Override
        public void run(String... args) {
            if (rolService.getAll().isEmpty()) {
                rolService.create(new Rol(Roltype.ADMINISTRADOR));
                rolService.create(new Rol(Roltype.OPERATOR));
                rolService.create(new Rol(Roltype.CONSULTANT));
                System.out.println("Roles iniciales creados.");
            }
        }
    }

