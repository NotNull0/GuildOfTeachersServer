package com.mpls.web2.model;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class ForumSectionContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private Boolean available;
    @OneToMany(mappedBy = "container", cascade = CascadeType.REMOVE)
    private List<ForumSection> sections;

    public ForumSectionContainer() {
    }

    public Long getId() {
        return id;
    }

    public ForumSectionContainer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public ForumSectionContainer setHeader(String header) {
        this.header = header;
        return this;
    }

    public List<ForumSection> getSections() {
        return sections;
    }

    public ForumSectionContainer setSections(List<ForumSection> sections) {
        this.sections = sections;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ForumSectionContainer setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "ForumSectionContainer{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", sections=" + sections.stream().map(ForumSection::getHeader).collect(toList()) +
                ", available=" + available +
                '}';
    }
}
