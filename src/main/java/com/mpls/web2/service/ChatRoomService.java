package com.mpls.web2.service;

import com.mpls.web2.dto.ChatRoomIntegerWrapper;
import com.mpls.web2.model.ChatRoom;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface ChatRoomService {

    ChatRoomIntegerWrapper findOneWithMessagesPageable(Long chatRoomId, Principal principal, Integer count);

    ChatRoom findOneAvailable(Long id);

    List<ChatRoom> findAllAvailable();

    ChatRoom save(ChatRoom chatRoom);

    ChatRoomIntegerWrapper update(ChatRoom chatRoom, Principal principal);

    Boolean delete(Long id);

    List<ChatRoom> findAll();

    ChatRoom findOne(Long id);

    List<ChatRoom> findAllByUserId(Long id);

    ChatRoom createOrFindChatRoom(Long userId, Principal principal);

    ChatRoom sendUserCreateChatRoom(ChatRoom chatRoom);
}
