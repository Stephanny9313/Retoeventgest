package com.example.eventgest.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name="programs")

public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id", nullable = false)
    private Long id;

    @Column(name="code",nullable = false)
    private String code;

    @Column(name="name",nullable = false,length = 20)
    private String name;

    @Column (name="staryear",nullable = false)
    private LocalDate staryear;

    @Column(name="endyear",nullable = false)
    private LocalDate endyear;

    @EnumeratedValue
    @Column(name="status",nullable = false)
    private Enum status;



    @OneToMany(
            mappedBy = "program",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Event> events = new ArrayList<>();



}
