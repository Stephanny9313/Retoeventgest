package com.example.eventgest.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Setter
@Getter
@Data
@Table(name="parameters")

public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id", nullable = false)
    private Long id;

    @Column(name = "clue", nullable = false, length = 50)
    private String clue;

    @Column(name="value",nullable = false, length = 200)
    private String Value;

    @Column(name = "type", nullable = false)
    private Enum type;

    @Column(name = "modifiable", nullable = false)
    private String modifiable;


}
