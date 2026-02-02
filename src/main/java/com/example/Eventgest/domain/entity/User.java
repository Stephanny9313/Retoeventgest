package com.example.Eventgest.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name="last_name",nullable = false, length = 200)
    private String last_name;

    @Column(name = "email", nullable = false,length=200)
    private String email;

    @Column(name = "password", nullable = false,length = 10)
    private String startTime;

    @Column(name = "phone", nullable = false)
    private Integer phone;



    @Column(nullable = false,length = 10)
    private Enum status;

    @Column(nullable = false)
    private Integer maxicapacity;



}
