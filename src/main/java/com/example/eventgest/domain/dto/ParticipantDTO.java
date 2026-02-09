package com.example.eventgest.domain.dto;

import com.example.eventgest.domain.enums.DocumentType;
import jakarta.validation.constraints.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;




@Data
public class ParticipantDTO {

    private Long id;
    private String name;
    private String documentType; // CC, TI, PP
    private Integer documentNumber;
    private String email;
    private String phone;
}
