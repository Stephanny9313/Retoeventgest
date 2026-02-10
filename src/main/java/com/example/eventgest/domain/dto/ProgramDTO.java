package com.example.eventgest.domain.dto;


import com.example.eventgest.domain.enums.ProgramStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProgramDTO {
    private Long id;
    private String code;
    private String name;
    private LocalDate starYear;
    private LocalDate endYear;
    private ProgramStatus status;

}
