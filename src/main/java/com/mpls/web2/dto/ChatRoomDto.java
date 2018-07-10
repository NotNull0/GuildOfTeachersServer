package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

import java.util.List;

@Dto
public class ChatRoomDto {

    private Long id;
    private List<ChatUserWrapperDto> users;
    private List<ChatMessageDto> messages;
    private Boolean available;

    public Long getId() {
        return id;
    }

    public ChatRoomDto setId(Long id) {
        this.id = id;
        return this;
    }

    public List<ChatUserWrapperDto> getUsers() {
        return users;
    }

    public ChatRoomDto setUsers(List<ChatUserWrapperDto> users) {
        this.users = users;
        return this;
    }

    public List<ChatMessageDto> getMessages() {
        return messages;
    }

    public ChatRoomDto setMessages(List<ChatMessageDto> messages) {
        this.messages = messages;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ChatRoomDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "ChatRoomDto{" +
                "id=" + id +
                ", users=" + users +
                ", messages=" + messages +
                ", available=" + available +
                '}';
    }
}
