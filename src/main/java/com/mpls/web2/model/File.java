package com.mpls.web2.model;

import com.mpls.web2.model.enums.FileType;

import javax.persistence.*;

@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String path;
    private Boolean available;
    private FileType fileType;
    @ManyToOne
    private ChatMessage chatMessage;
    @ManyToOne
    private ForumMessage forumMessage;
    @ManyToOne
    private Comment comment;
    @ManyToOne
    private User user;

    public File() {
    }

    public Long getId() {
        return id;
    }

    public File setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public File setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public File setPath(String path) {
        this.path = path;
        return this;
    }

    public FileType getFileType() {
        return fileType;
    }

    public File setFileType(FileType fileType) {
        this.fileType = fileType;
        return this;
    }

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public File setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public File setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
        return this;
    }

    public File setForumMessage(ForumMessage forumMessage) {
        this.forumMessage = forumMessage;
        return this;
    }

    public File setComment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public File setUser(User user) {
        this.user = user;
        return this;
    }

    public ForumMessage getForumMessage() {
        return forumMessage;
    }


    public Comment getComment() {
        return comment;
    }


    public User getUser() {
        return user;
    }


    public Boolean getAvailable() {
        return available;
    }


    public boolean isAvailable() {
        return available;
    }

    public File setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", chatMessage=" + chatMessage.getId() +
                ", forumMessage=" + forumMessage.getId() +
                ", fileType=" + fileType +
                ", comment=" + comment.getId() +
                ", user=" + user.getName() + " " + user.getLastname() + " " + user.getSurname() +
                ", available=" + available +
                '}';
    }
}
