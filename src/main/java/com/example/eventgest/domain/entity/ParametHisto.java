package com.example.eventgest.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
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

}
