package com.mpls.web2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.model.utils.DateSerializer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private Timestamp datetime;
    @Column(columnDefinition = "LONGTEXT")
    private String text;
    private Boolean available;
    private String image;
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public Article setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public Article setHeader(String header) {
        this.header = header;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    public Article setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getText() {
        return text;
    }

    public Article setText(String text) {
        this.text = text;
        return this;
    }


    public Boolean getAvailable() {
        return available;
    }

    public Article setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Article setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Article setImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", datetime=" + datetime +
                ", text='" + text + '\'' +
                ", available=" + available +
                ", image='" + image + '\'' +
                ", comments=" + comments +
                '}';
    }
}
