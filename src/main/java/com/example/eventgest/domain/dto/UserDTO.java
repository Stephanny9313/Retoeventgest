package com.example.eventgest.domain.dto;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserDTO {


    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Integer phone;
    private String status;
    private Integer maxCapacity;


}
