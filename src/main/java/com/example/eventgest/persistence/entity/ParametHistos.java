package com.example.eventgest.persistence.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;



import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "paramethistos")
public class ParametHistos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paramethisto_id")
    private Long id;

    @Column(name = "previous_value", nullable = false, length = 50)
    private String previousValue;

    @Column(name = "new_value", nullable = false, length = 200)
    private String newValue;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parameter_id", nullable = false)
    private Parameter parameter;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



}


