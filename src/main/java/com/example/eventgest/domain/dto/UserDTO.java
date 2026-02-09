package com.example.eventgest.domain.dto;


import com.example.eventgest.domain.enums.Roltype;
import com.example.eventgest.domain.enums.UserStatus;
import com.example.eventgest.persistence.entity.Rol;
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
    private UserStatus status;
    private Roltype roltype;



}
