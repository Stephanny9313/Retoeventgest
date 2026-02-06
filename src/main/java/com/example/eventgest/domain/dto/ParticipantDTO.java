package com.example.eventgest.domain.dto;

import com.example.eventgest.domain.enums.DocumentType;
import jakarta.validation.constraints.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;




@Data
public class ParticipantDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String name;

    @NotNull(message = "El tipo de documento es obligatorio")
    private String documentType; // Lo dejamos como String para convertirlo al Enum en service

    @NotNull(message = "El número de documento es obligatorio")
    @Min(value = 1, message = "El número de documento debe ser positivo")
    private Integer documentNumber;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser válido")
    @Size(max = 50, message = "El correo no puede superar 50 caracteres")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede superar 20 caracteres")
    private String phone;
}
