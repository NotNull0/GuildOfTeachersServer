package com.mpls.web2.model;

import javax.persistence.*;

@Entity
public class CourseVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rating;
    private Boolean available;
    @OneToOne
    private Course course;
    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public CourseVote setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public CourseVote setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public Course getCourse() {
        return course;
    }

    public CourseVote setCourse(Course course) {
        this.course = course;
        return this;
    }

    public User getUser() {
        return user;
    }

    public CourseVote setUser(User user) {
        this.user = user;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public CourseVote setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "CourseVote{" +
                "id=" + id +
                ", rating=" + rating +
                ", available=" + available +
                ", course=" + course.getHeader() +
                ", user=" + user.getName() + " " + user.getLastname() + " " + user.getSurname() +
                '}';
    }
}
