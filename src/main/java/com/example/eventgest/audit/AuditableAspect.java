package com.example.eventgest.audit;

import com.example.eventgest.domain.service.Impl.AuditService;
import com.example.eventgest.domain.repository.UserRepository;
import com.example.eventgest.persistence.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditableAspect {


    private static final Logger log =
            LoggerFactory.getLogger(AuditableAspect.class);

    private final AuditService auditService;
    private final UserRepository userRepository;

    public AuditableAspect(AuditService auditService, UserRepository userRepository) {
        this.auditService = auditService;
        this.userRepository = userRepository;
    }

    @AfterReturning("@annotation(com.example.eventgest.audit.Auditable)")
    public void logAfter(JoinPoint joinPoint) {
        try {
            Auditable auditable = getAuditableAnnotation(joinPoint);
            if (auditable == null) return;

            Long userId = getCurrentUserId();
            if (userId == null) return;

            Long entityId = extractEntityId(joinPoint.getArgs());

            String description = auditable.action() + " " + auditable.entity();
            if (entityId != null) {
                description += " (id=" + entityId + ")";
            }

            auditService.registerAction(
                    userId,
                    auditable.action(),
                    description,
                    entityId
            );

        } catch (Exception e) {
            log.error("Error ejecutando auditoría", e);
        }
    }

    private Auditable getAuditableAnnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(Auditable.class);
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;

        String email = auth.getName();

        return userRepository.findByEmailIgnoreCase(email)
                .map(User::getId)
                .orElse(null);
    }

    private Long extractEntityId(Object[] args) {
        if (args == null) return null;
        for (Object arg : args) {
            if (arg instanceof Long) {
                return (Long) arg;
            }
        }
        return null;
    }
}
