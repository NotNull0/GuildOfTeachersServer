package com.mpls.web2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.model.utils.DateSerializer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class ForumMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp datetime;
    private Boolean available;
    @Column(columnDefinition = "LONGTEXT")
    private String text;
    @OneToOne
    private User from;
    @ManyToOne
    private ForumTopic forumTopic;
    @OneToMany(mappedBy = "forumMessage", cascade = CascadeType.REMOVE)
    private List<File> files;

    public ForumMessage() {
    }

    public Long getId() {
        return id;
    }

    public ForumMessage setId(Long id) {
        this.id = id;
        return this;
    }

    //    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    public ForumMessage setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public User getFrom() {
        return from;
    }

    public ForumMessage setFrom(User from) {
        this.from = from;
        return this;
    }

    public String getText() {
        return text;
    }

    public ForumMessage setText(String text) {
        this.text = text;
        return this;
    }

    public ForumTopic getForumTopic() {
        return forumTopic;
    }

    public ForumMessage setForumTopic(ForumTopic forumTopic) {
        this.forumTopic = forumTopic;
        return this;
    }

    public List<File> getFiles() {
        return files;
    }

    public ForumMessage setFiles(List<File> files) {
        this.files = files;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ForumMessage setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "ForumMessage{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", from=" + from.getName() + " " + from.getLastname() + " " + from.getSurname() +
                ", text=" + text +
                ", forumTopic=" + forumTopic.getId() +
                ", files=" + files.stream().map(File::getName).collect(toList()) +
                ", available=" + available +
                '}';
    }
}
