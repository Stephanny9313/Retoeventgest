package com.example.eventgest.persistence.entity;



import com.example.eventgest.domain.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_type", nullable = false, unique = true, length = 50)
    private UserStatus rolType;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}











