package com.example.eventgest.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name="last_name",nullable = false, length = 200)
    private String last_name;

    @Column(name = "email", nullable = false,length=200)
    private String email;

    @Column(name = "password", nullable = false,length = 10)
    private String startTime;

    @Column(name = "phone", nullable = false)
    private Integer phone;



    @Column(nullable = false,length = 10)
    private Enum status;

    @Column(nullable = false)
    private Integer maxicapacity;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Audit> audits = new ArrayList<>();

    //rol
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    //event
    @OneToMany(mappedBy = "event")
    private List<Event> events = new ArrayList<>();

    //typeevent
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "eventtype_id", nullable = false)
    private Eventtype eventtype;


}
