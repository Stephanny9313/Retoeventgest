package com.example.eventgest.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id", nullable = false)
    private Long id;

    @Column(name="name",nullable = false)
    private String code;

    @Column(name="documenttype",nullable = false,length = 20)
    private Enum documentType ;

    @Column(name="documentnumber",nullable = false)
    private Integer documentNumber;

    @Column(name="email",nullable=false,length = 20)
    private String email;

    @Column(name="phone",nullable=false)
    private Integer phone;
}
