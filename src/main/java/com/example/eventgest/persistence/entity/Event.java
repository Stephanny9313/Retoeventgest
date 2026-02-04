package com.example.eventgest.persistence.entity;

import com.example.eventgest.domain.enums.EventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.example.eventgest.persistence.entity.Event;


@Getter
@Setter
@Entity
@Table(name = "events")

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_name", nullable = false, length = 50)
    private String eventName;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "guest_count", nullable = false)
    private Integer guestCount;

    @Column(nullable = false, length = 100)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EventStatus status;

    @Column(name = "max_capacity", nullable = false)
    private Integer maxCapacity;

    // MUCHOS eventos pertenecen a UN usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //  Un evento tiene muchas inscripciones
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations;

    //program
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventtype_id", nullable = false)
    private EventType eventType;


}


