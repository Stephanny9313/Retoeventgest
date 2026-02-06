package com.example.eventgest.domain.dto;

import lombok.Data;

@Data
public class ParameterDTO {

    private Long id;
    private String clue;
    private String value;
    private String type;      // mejor String para mapear con entity
    private Boolean modifiable; // cambiar a Boolean si representa un flag

}

