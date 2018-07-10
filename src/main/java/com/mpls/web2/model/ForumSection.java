package com.mpls.web2.model;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class ForumSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private Boolean available;
    @OneToMany(mappedBy = "forumSection", cascade = CascadeType.REMOVE)
    private List<ForumTopic> forumTopics;
    @ManyToOne
    private ForumSectionContainer container;

    public ForumSection() {
    }

    public Long getId() {
        return id;
    }

    public ForumSection setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public ForumSection setHeader(String header) {
        this.header = header;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ForumSection setDescription(String description) {
        this.description = description;
        return this;
    }

    public ForumSectionContainer getContainer() {
        return container;
    }

    public ForumSection setContainer(ForumSectionContainer container) {
        this.container = container;
        return this;
    }

    public List<ForumTopic> getForumTopics() {
        return forumTopics;
    }

    public void setForumTopics(List<ForumTopic> forumTopics) {
        this.forumTopics = forumTopics;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ForumSection{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", forumTopics=" + forumTopics.stream().map(ForumTopic::getHeader).collect(toList()) +
                ", container=" + container.getHeader() +
                ", available=" + available +
                '}';
    }
}
