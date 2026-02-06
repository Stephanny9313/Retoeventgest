package com.example.eventgest.audit;

import com.example.eventgest.domain.service.Impl.AuditService;
import com.example.eventgest.domain.repository.UserRepository;
import com.example.eventgest.persistence.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditableAspect {

    private final AuditService auditService;
    private final UserRepository userRepository;

    public AuditableAspect(AuditService auditService, UserRepository userRepository) {
        this.auditService = auditService;
        this.userRepository = userRepository;
    }

    @AfterReturning("@annotation(auditable)")
    public void logAfter(JoinPoint joinPoint, Auditable auditable) {
        Long userId = getCurrentUserId();

        // Intentamos obtener el ID de la entidad afectada (si se pasa como parámetro)
        Object[] args = joinPoint.getArgs();
        Long entityId = null;
        for (Object arg : args) {
            if (arg instanceof Long) {
                entityId = (Long) arg;
                break;
            }
        }

        String description = auditable.action() + " en " + auditable.entity();
        if (entityId != null) {
            description += " con id: " + entityId;
        }

        auditService.log(userId, auditable.action(), description, entityId);
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return user.getId();
    }
}
