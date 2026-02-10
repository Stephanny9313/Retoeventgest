package com.example.eventgest.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProgramStatus {
    PLANNED("PLANIFICADO"),
    ACTIVE("ACTIVO"),
    CLOSED("CERRADO"),
    CANCELLED("CANCELADO");

    private final String value;

    ProgramStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ProgramStatus fromValue(String value) {
        for (ProgramStatus status : ProgramStatus.values()) {
            if (status.value.equals(value) || status.name().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor de estado no válido: " + value);
    }
}
