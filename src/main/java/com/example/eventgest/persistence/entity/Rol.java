package com.example.eventgest.persistence.entity;



import com.example.eventgest.domain.enums.Roltype;
import com.example.eventgest.domain.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@Getter
@Setter
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_type", nullable = false, unique = true)
    private Roltype rolType;

    public Rol() {}

    public Rol(Roltype rolType) {
        this.rolType = rolType;
    }

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}











