package com.mpls.web2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class ForumTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private Boolean available = true;
    private Timestamp datetime;
    @Column(columnDefinition = "LONGTEXT")
    private String text;
    @OneToOne
    private User author;
    @ManyToOne
    private ForumSection forumSection;
    @OneToMany(mappedBy = "forumTopic", cascade = CascadeType.REMOVE)
    private List<ForumMessage> messages;

    public ForumTopic() {
    }

    public Long getId() {
        return id;
    }

    public ForumTopic setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public ForumTopic setHeader(String header) {
        this.header = header;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ForumTopic setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getText() {
        return text;
    }

    public ForumTopic setText(String text) {
        this.text = text;
        return this;
    }

    public ForumSection getForumSection() {
        return forumSection;
    }

    public ForumTopic setForumSection(ForumSection forumSection) {
        this.forumSection = forumSection;
        return this;
    }

    public List<ForumMessage> getMessages() {
        return messages;
    }

    public ForumTopic setMessages(List<ForumMessage> messages) {
        this.messages = messages;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
//    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    public ForumTopic setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }


    @Override
    public String toString() {
        return "ForumTopic{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", available=" + available +
                ", datetime=" + datetime +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", forumSection=" + forumSection +
//                ", messages=" + messages +
                '}';
    }
}
