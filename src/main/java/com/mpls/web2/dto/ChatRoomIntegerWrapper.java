package com.mpls.web2.dto;

public class ChatRoomIntegerWrapper {

    private ChatRoomDto chatRoom;
    private Integer count;

    public ChatRoomDto getChatRoom() {
        return chatRoom;
    }

    public ChatRoomIntegerWrapper setChatRoom(ChatRoomDto chatRoom) {
        this.chatRoom = chatRoom;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public ChatRoomIntegerWrapper setCount(Integer count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return "ChatRoomIntegerWrapper{" +
                "chatRoom=" + chatRoom.getId() +
                ", count=" + count +
                '}';
    }
}
