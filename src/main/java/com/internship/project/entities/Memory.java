package com.internship.project.entities;

import javax.persistence.*;

@Entity
public class Memory {

    @Id
    private Long id;

    @Column(name = "memory")
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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