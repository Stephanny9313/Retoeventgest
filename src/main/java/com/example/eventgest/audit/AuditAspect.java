package com.example.eventgest.audit;

import com.example.eventgest.domain.service.Impl.AuditService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    private final AuditService auditService;

    public AuditAspect(AuditService auditService) {
        this.auditService = auditService;
    }

    @AfterReturning("@annotation(auditable)")
    public void audit(JoinPoint joinPoint, Auditable auditable) {

        Object[] args = joinPoint.getArgs();

        Long entityId = extractFirstLong(args);
        Long userId   = extractLastLong(args);

        if (userId == null) {
            throw new IllegalStateException("No se pudo extraer userId para auditoría");
        }



        auditService.log(
                userId,
                auditable.action(),
                auditable.entity(),
                entityId
        );
    }

    // ==========================
    // MÉTODOS AUXILIARES PRIVADOS
    // ==========================

    private Long extractFirstLong(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof Long) {
                return (Long) arg;
            }
        }
        return null;
    }

    private Long extractLastLong(Object[] args) {
        for (int i = args.length - 1; i >= 0; i--) {
            if (args[i] instanceof Long) {
                return (Long) args[i];
            }
        }
        return null;
    }
}
