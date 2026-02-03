package com.example.eventgest.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Table(name="paramet_history")

public class ParametHisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id", nullable = false)
    private Long id;

    @Column(name = "previous_value", nullable = false, length = 50)
    private String previousValue;

    @Column(name="new_value",nullable = false, length = 200)
    private String newValue;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "parameter_id")
    private Parameter parameter;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Parameter user;


}
