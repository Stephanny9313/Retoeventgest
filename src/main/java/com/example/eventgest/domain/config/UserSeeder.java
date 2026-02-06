package com.example.eventgest.domain.config;

import com.example.eventgest.domain.enums.Roltype;
import com.example.eventgest.domain.enums.UserStatus;
import com.example.eventgest.domain.service.Impl.RolServiceImpl;
import com.example.eventgest.domain.service.Impl.UserServiceImpl;
import com.example.eventgest.persistence.entity.Rol;
import com.example.eventgest.persistence.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class UserSeeder implements CommandLineRunner {

    private final UserServiceImpl userService;
    private final RolServiceImpl rolService;
    private final PasswordEncoder passwordEncoder;

    public UserSeeder(UserServiceImpl userService, RolServiceImpl rolService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.rolService = rolService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userService.getAll().isEmpty()) {

            List<Rol> roles = rolService.getAll();

            if (roles.isEmpty()) {
                System.out.println("No hay roles disponibles. Ejecuta primero RoleSeeder.");
                return;
            }

            // ADMIN
            Rol adminRol = roles.stream()
                    .filter(r -> r.getRolType() == Roltype.ADMINISTRADOR)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Rol ADMINISTRADOR no encontrado"));

            User admin = new User();
            admin.setName("Alice");
            admin.setLastName("Johnson");
            admin.setEmail("alice@gmail.com");
            admin.setPhone("3001234567");
            admin.setStatus(UserStatus.ACTIVE);
            admin.setPassword(passwordEncoder.encode("Alice123"));
            admin.setRol(adminRol);
            userService.create(admin);

            // OPERATOR
            Rol operatorRol = roles.stream()
                    .filter(r -> r.getRolType() == Roltype.OPERATOR)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Rol OPERATOR no encontrado"));

            User operator = new User();
            operator.setName("Bob");
            operator.setLastName("Smith");
            operator.setEmail("bob@gmail.com");
            operator.setPhone("3009876543");
            operator.setStatus(UserStatus.ACTIVE);
            operator.setPassword(passwordEncoder.encode("Bob123"));
            operator.setRol(operatorRol);
            userService.create(operator);

            // CONSULTANT
            Rol consultantRol = roles.stream()
                    .filter(r -> r.getRolType() == Roltype.CONSULTANT)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Rol CONSULTANT no encontrado"));

            User consultant = new User();
            consultant.setName("Carol");
            consultant.setLastName("Davis");
            consultant.setEmail("carol@gmail.com");
            consultant.setPhone("3005551122");
            consultant.setStatus(UserStatus.ACTIVE);
            consultant.setPassword(passwordEncoder.encode("Carol123"));
            consultant.setRol(consultantRol);
            userService.create(consultant);

            System.out.println("Seeder de usuarios ejecutado correctamente.");
        }
    }
}
