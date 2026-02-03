package com.example.eventgest.domain.dto;


import lombok.Data;

@Data
public class ParameterDTO {

    private Long id;
    private String clue;
    private String value;
    private Enum type;
    private String modifiable;

}
