package com.mpls.web2.model;

import javax.persistence.*;

@Entity
public class TeachingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean available = true;
    @OneToOne(cascade = CascadeType.REMOVE)
    private File file;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private TeachingProgramContainer container;

    public TeachingProgram() {
    }

    public Long getId() {
        return id;
    }

    public TeachingProgram setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeachingProgram setName(String name) {
        this.name = name;
        return this;
    }

    public File getFile() {
        return file;
    }

    public TeachingProgram setFile(File file) {
        this.file = file;
        return this;
    }

    public TeachingProgramContainer getContainer() {
        return container;
    }

    public TeachingProgram setContainer(TeachingProgramContainer container) {
        this.container = container;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public TeachingProgram setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "TeachingProgram{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", file=" + file.getName() +
                ", container=" + container.getName() +
                ", available=" + available +
                '}';
    }
}
