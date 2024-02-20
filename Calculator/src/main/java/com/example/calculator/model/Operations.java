package com.example.calculator.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Operations {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column
    private String operation;
    
    @Column
    private List<Double> operands;
    
    @Column
    private String expression;

    @Column
    private double result;
}
