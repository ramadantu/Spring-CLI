package com.internship.project.entities;

import javax.persistence.*;

@Entity
public class Memory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    public Long getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Memory() {
    }

    public Memory(Double value) {
        setValue(value);
    }
}