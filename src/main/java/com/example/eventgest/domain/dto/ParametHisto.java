package com.example.eventgest.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class ParametHisto {

    private Long Id;
    private String previousValue;
    private String newValue;
    private LocalDate date;



}
