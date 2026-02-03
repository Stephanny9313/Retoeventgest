package com.example.eventgest.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;


}
