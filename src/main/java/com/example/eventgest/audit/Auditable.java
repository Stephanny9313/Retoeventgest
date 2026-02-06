package com.example.eventgest.audit;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  Auditable {

    String action();     // CREAR, EDITAR, PUBLICAR, CERRAR, ELIMINAR
    String entity();     // EVENTO, PROGRAMA, PARTICIPANTE, etc.
}

