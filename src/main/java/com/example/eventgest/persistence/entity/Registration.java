package com.example.eventgest.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="registrations")

public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="registration_id",nullable = false)
    private Long id;

    @Column(name="registration_date",nullable = false)
    private Date resgistrationDate;

    @Column(name="attendace",nullable = false)
    private Enum attendance;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;





}
