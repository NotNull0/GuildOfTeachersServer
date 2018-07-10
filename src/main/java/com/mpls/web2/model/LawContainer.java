package com.mpls.web2.model;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class LawContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean available = true;
    @OneToMany(mappedBy = "container")
    private List<Law> laws;

    public LawContainer() {
    }

    public Long getId() {
        return id;
    }

    public LawContainer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LawContainer setName(String name) {
        this.name = name;
        return this;
    }

    public List<Law> getLaws() {
        return laws;
    }

    public LawContainer setLaws(List<Law> laws) {
        this.laws = laws;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public LawContainer setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "LawContainer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", laws=" + laws.stream().map(Law::getName).collect(toList()) +
                ", available=" + available +
                '}';
    }
}
