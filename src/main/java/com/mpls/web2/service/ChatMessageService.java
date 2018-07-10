package com.mpls.web2.service;

import com.mpls.web2.model.ChatMessage;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface ChatMessageService {

    List<ChatMessage> findAllByChatRoomIdAndChangeRead(Long chatRoomId, Principal principal, Integer count);

    ChatMessage findOneAvailable(Long id);

    ChatMessage findOne(Long id);

    ChatMessage save(ChatMessage chatMessage);

    ChatMessage update(ChatMessage chatMessage);

    Boolean delete(Long id);

    List<ChatMessage> findAllByFromId(Long id);

    List<ChatMessage> findAllAvailable();

    List<ChatMessage> findAll();

    ChatMessage sendChatMessage(Long chatRoomId, ChatMessage chatMessage, Principal principal);

    List<ChatMessage> findAllByFromIdAndRead(Long id, Boolean read);

    Integer countAllUnreadByChatRoom(Long chatRoomId, Principal principal);

    Integer countAllUnread(Principal principal);
}
