package com.example.eventgest.persistence.entity;

import com.example.eventgest.domain.enums.EventStatus;
import com.example.eventgest.persistence.entity.EventType;
import com.example.eventgest.persistence.entity.Program;
import com.example.eventgest.persistence.entity.Registration;
import com.example.eventgest.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="event_name", nullable = false, length = 120)
    private String title;

    @Column(length = 300)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(nullable = false, length = 120)
    private String place;

    private Integer maxCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventType eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations;

    // Auditoría
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }
    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }
}
