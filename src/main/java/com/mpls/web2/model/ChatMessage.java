package com.mpls.web2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.model.utils.DateSerializer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp datetime;
    @Column(columnDefinition = "LONGTEXT")
    private String text;
    private Boolean available;
    private Boolean hasBeenRead = false;
    @OneToOne
    private User from;
    @OneToMany(mappedBy = "chatMessage", cascade = CascadeType.REMOVE)
    private List<File> files;
    @ManyToOne
    private ChatRoom chatRoom;

    public ChatMessage() {
    }

    public Long getId() {
        return id;
    }

    public ChatMessage setId(Long id) {
        this.id = id;
        return this;
    }

    //    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    public ChatMessage setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public Boolean getHasBeenRead() {
        return hasBeenRead;
    }

    public ChatMessage setHasBeenRead(Boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
        return this;
    }

    public String getText() {
        return text;
    }

    public ChatMessage setText(String text) {
        this.text = text;
        return this;
    }

    public User getFrom() {
        return from;
    }

    public ChatMessage setFrom(User from) {
        this.from = from;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ChatMessage setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<File> getFiles() {
        return files;
    }

    public ChatMessage setFiles(List<File> files) {
        this.files = files;
        return this;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public ChatMessage setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        return this;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", text='" + text + '\'' +
                ", available=" + available +
                ", read=" + hasBeenRead +
                ", from=" + from.getName() + " " + from.getSurname() + " " + from.getLastname() +
                ", files=" + files.stream().map(file -> file.getName()).collect(toList()) +
                ", chatRoom=" + chatRoom +
                '}';
    }
}
