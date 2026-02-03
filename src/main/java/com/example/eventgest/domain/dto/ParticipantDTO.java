package com.example.eventgest.domain.dto;


import lombok.Data;

@Data

public class ParticipantDTO {

    private Long id;
    private String name;
    private Enum documentType ;
    private Integer documentNumber;
    private String email;
    private Integer phone;

}
