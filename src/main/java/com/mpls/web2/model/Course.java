package com.mpls.web2.model;

import com.mpls.web2.model.enums.CourseType;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    private Integer rating;
    private String link;
    private CourseType type;
    private Boolean available;
    @OneToOne
    private CourseCategory courseCategory;
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    private Integer userRating;

    private Boolean canVote;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public Course setHeader(String header) {
        this.header = header;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Course setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Course setImage(String image) {
        this.image = image;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public Course setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Course setLink(String link) {
        this.link = link;
        return this;
    }

    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public Course setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }

    public CourseType getType() {
        return type;
    }

    public Course setType(CourseType type) {
        this.type = type;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Course setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Course setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Boolean getCanVote() {
        return canVote;
    }

    public Course setCanVote(Boolean canVote) {
        this.canVote = canVote;
        return this;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public Course setUserRating(Integer userRating) {
        this.userRating = userRating;
        return this;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", link='" + link + '\'' +
                ", type=" + type +
                ", available=" + available +
                ", courseCategory=" + courseCategory.getName() +
//                ", comments=" + comments.stream().map(Comment::getId).collect(toList()) +
                ", userRating=" + userRating +
                ", canVote=" + canVote +
                '}';
    }
}
