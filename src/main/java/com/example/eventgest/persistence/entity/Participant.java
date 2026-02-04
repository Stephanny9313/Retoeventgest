package com.example.eventgest.persistence.entity;

import com.example.eventgest.domain.enums.DocumentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name= "participants")
public class    Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id", nullable = false)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @EnumeratedValue
    @Column(name="documenttype",nullable = false,length = 20)
    private DocumentType documentType ;

    @Column(name="documentnumber",nullable = false)
    private Integer documentNumber;

    @Column(name="email",nullable=false,length = 20)
    private String email;

    @Column(name="phone",nullable=false)
    private Integer phone;


    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations = new ArrayList<>();


}

