package com.mpls.web2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "chat_room_user",
            joinColumns = @JoinColumn(name = "chat_room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<ChatUserWrapper> users;
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private List<ChatMessage> messages;
    private Boolean available;

    public ChatRoom() {
        this.messages = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public ChatRoom setId(Long id) {
        this.id = id;
        return this;
    }

    public List<ChatUserWrapper> getUsers() {
        return users;
    }

    public ChatRoom setUsers(List<ChatUserWrapper> users) {
        this.users = users;
        return this;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public ChatRoom setMessages(List<ChatMessage> messages) {
        this.messages = messages;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ChatRoom setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "id=" + id +
                ", users=" + users.stream().map(ChatUserWrapper::getId).collect(toList()) +
//                ", messages=" + messages.stream().map(ChatMessage::getId).collect(toList()) +
                ", available=" + available +
                '}';
    }
}
