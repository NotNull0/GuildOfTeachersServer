package com.mpls.web2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlaceOfWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean available = true;

    public PlaceOfWork() {
    }

    public Long getId() {
        return id;
    }

    public PlaceOfWork setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlaceOfWork setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public PlaceOfWork setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "PlaceOfWork{" +
                "id=" + id +
                ", name=" + (name == null ? "null" : name) +
                ", available=" + available +
                '}';
    }
}
