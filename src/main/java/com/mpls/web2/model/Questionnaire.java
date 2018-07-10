package com.mpls.web2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "questionnaire")
    private List<CourseCategory> courseCategories;

    @JsonIgnore
    @ManyToMany
    private List<User> users;

    private Boolean canVote;

    private Boolean available;

    public Long getId() {
        return id;
    }

    public Questionnaire setId(Long id) {
        this.id = id;
        return this;
    }

    public List<CourseCategory> getCourseCategories() {
        return courseCategories;
    }

    public Questionnaire setCourseCategories(List<CourseCategory> courseCategories) {
        this.courseCategories = courseCategories;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Questionnaire setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Questionnaire setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Boolean getCanVote() {
        return canVote;
    }

    public Questionnaire setCanVote(Boolean canVote) {
        this.canVote = canVote;
        return this;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", courseCategories=" + courseCategories.stream().map(courseCategory -> courseCategory.getName()).collect(toList()) +
                ", users=" + users +
                ", canVote=" + canVote +
                ", available=" + available +
                '}';
    }
}
