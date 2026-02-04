package com.example.eventgest.persistence.entity;


import com.example.eventgest.domain.enums.AttendanceStatus;
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
    @Column(name = "registration_id", nullable = false)
    private Long id;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "attendance", nullable = false, length = 20)
    private AttendanceStatus attendance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;


}
