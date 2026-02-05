package com.example.eventgest.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

import java.util.List;


@Setter
@Getter
@Data
@Entity
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

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(mappedBy = "parameter")
    private List<ParametHistos> parametHisto;







}
