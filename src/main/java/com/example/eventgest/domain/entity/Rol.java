package com.example.eventgest.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Data
@Table(name="roles")

public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id", nullable = false)
    private Long id;

    @Column(name = "roltype", nullable = false, length = 50)
    private String roltype;

    //user
    @OneToMany(mappedBy = "rol")
    private List<User> usuarios = new ArrayList<>();



}

