package com.example.eventgest.persistence.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "event_types")
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventtype_id", nullable = false)
    private Long id;

    @Column(name = "type_name", nullable = false, length = 50, unique = true)
    private String typeName;




}

