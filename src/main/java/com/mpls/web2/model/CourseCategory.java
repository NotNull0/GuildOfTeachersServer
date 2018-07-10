package com.mpls.web2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class CourseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double count;
    private Boolean available;
    @JsonIgnore
    @ManyToOne
    private Questionnaire questionnaire;

    public CourseCategory() {
    }

    public Long getId() {
        return id;
    }

    public CourseCategory setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseCategory setName(String name) {
        this.name = name;
        return this;
    }

    public Double getCount() {
        return count;
    }

    public CourseCategory setCount(Double count) {
        this.count = count;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public CourseCategory setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public CourseCategory setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
        return this;
    }

    @Override
    public String toString() {
        return "CourseCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", available=" + available +
                ", questionnaire=" + getQuestionnaire().getId() +
                '}';
    }
}
