package com.example.eventgest.domain.dto;


import com.example.eventgest.domain.enums.Roltype;
import lombok.Data;

@Data
public class RolDTO {
    private Long id;
    private Roltype name;

}
