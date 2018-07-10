package com.mpls.web2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.model.utils.DateSerializer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp datetime;
    @Column(columnDefinition = "LONGTEXT")
    private String text;
    private Boolean available;
    @OneToOne
    private User from;
    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<File> files;
    @ManyToOne
    private Article article;
    @ManyToOne
    private Course course;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    //    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    public Comment setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getText() {
        return text;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    public User getFrom() {
        return from;
    }

    public Comment setFrom(User from) {
        this.from = from;
        return this;
    }

    public List<File> getFiles() {
        return files;
    }

    public Comment setFiles(List<File> files) {
        this.files = files;
        return this;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Comment setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", text='" + text + '\'' +
                ", from=" + from.getName() + " " + from.getLastname() + " " + from.getSurname() +
                ", files=" + files.stream().map(File::getName).collect(toList()) +
                ", available=" + available +
                '}';
    }
}
