package com.mpls.web2.model;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class TeachingProgramContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean available = true;
    @OneToMany(mappedBy = "container",cascade = CascadeType.REMOVE)
    private List<TeachingProgram> programs;

    public TeachingProgramContainer() {
    }

    public Long getId() {
        return id;
    }

    public TeachingProgramContainer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeachingProgramContainer setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public TeachingProgramContainer setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<TeachingProgram> getPrograms() {
        return programs;
    }

    public TeachingProgramContainer setPrograms(List<TeachingProgram> programs) {
        this.programs = programs;
        return this;
    }

    @Override
    public String toString() {
        return "TeachingProgramContainer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                ", programs=" + programs.stream().map(TeachingProgram::getName).collect(toList()) +
                '}';
    }
}
