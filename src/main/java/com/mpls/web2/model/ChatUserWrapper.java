package com.mpls.web2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.model.utils.DateSerializer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class ChatUserWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp datetime;
    private Boolean available;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "chat_room_user",
            inverseJoinColumns = @JoinColumn(name = "chat_room_id"),
            joinColumns = @JoinColumn(name = "user_id"))
    private List<ChatRoom> chatRooms;
    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    public ChatUserWrapper() {
    }

    public Long getId() {
        return id;
    }

    public ChatUserWrapper setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ChatUserWrapper setUser(User user) {
        this.user = user;
        return this;
    }

    //    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    public ChatUserWrapper setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public List<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public ChatUserWrapper setChatRooms(List<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ChatUserWrapper setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "ChatUserWrapper{" +
                "id=" + id +
                ", chatRooms=" + chatRooms.stream().map(ChatRoom::getId).collect(toList()) +
                ", user=" + user.getName() + " " + user.getLastname() + " " + user.getSurname() +
                ", datetime=" + datetime +
                ", available=" + available +
                '}';
    }
}
