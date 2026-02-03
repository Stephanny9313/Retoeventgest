package com.example.eventgest.persistence.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @Column(name = "rol_type", nullable = false, unique = true, length = 50)
    private String rolType;

    @OneToMany(mappedBy = "rol")
    private List<Event> rol;



}




