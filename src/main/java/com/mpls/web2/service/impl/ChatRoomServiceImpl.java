package com.mpls.web2.service.impl;

import com.mpls.web2.dto.ChatMessageDto;
import com.mpls.web2.dto.ChatRoomDto;
import com.mpls.web2.dto.ChatRoomIntegerWrapper;
import com.mpls.web2.model.ChatMessage;
import com.mpls.web2.model.ChatRoom;
import com.mpls.web2.model.ChatUserWrapper;
import com.mpls.web2.model.User;
import com.mpls.web2.repository.ChatRoomRepository;
import com.mpls.web2.service.ChatMessageService;
import com.mpls.web2.service.ChatRoomService;
import com.mpls.web2.service.ChatUserWrapperService;
import com.mpls.web2.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static com.mpls.web2.service.exceptions.Validation.checkId;
import static com.mpls.web2.service.exceptions.Validation.checkUpdate;
import static java.util.stream.Collectors.toList;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private static final Logger LOGGER = Logger.getLogger(ChatRoomServiceImpl.class);

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatUserWrapperService chatUserWrapperService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private SimpMessagingTemplate template;

    private ChatRoom sortByDate(ChatRoom chatRoom) {
        List<ChatMessage> chatMessages = chatRoom.getMessages();
        if (chatMessages.size() == 0) {
            return chatRoom;
        }
        chatMessages.sort((p1, p2) -> p1.getDatetime().compareTo(p2.getDatetime()));
        chatRoom.setMessages(chatMessages);
        return chatRoom;
    }

    @Override
    public ChatRoomIntegerWrapper findOneWithMessagesPageable(Long chatRoomId, Principal principal, Integer count) {
        Integer countUnread = chatMessageService.countAllUnreadByChatRoom(chatRoomId, principal);
        return new ChatRoomIntegerWrapper()
                .setChatRoom(map(findOne(chatRoomId)
                        .setMessages(chatMessageService.findAllByChatRoomIdAndChangeRead(chatRoomId, principal, count)), ChatRoomDto.class))
                .setCount(countUnread);
    }

    @Override
    public ChatRoom findOneAvailable(Long id) {
        checkId(id);
        return sortByDate(chatRoomRepository.findByAvailableAndId(true, id));
    }

    @Override
    public List<ChatRoom> findAllAvailable() {
        return chatRoomRepository.findAllByAvailable(true);
    }

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public ChatRoomIntegerWrapper update(ChatRoom chatRoom, Principal principal) {
        checkUpdate(chatRoom.getId(), chatRoomRepository);
        User user = userService.findByEmail(principal.getName());
        Integer count = chatMessageService.countAllUnreadByChatRoom(chatRoom.getId(), principal);
        return new ChatRoomIntegerWrapper()
                .setChatRoom(map(chatRoomRepository.save(chatRoom.setMessages(chatRoom.getMessages().stream()
                        .peek(chatMessage -> LOGGER.info(chatMessage.getFrom().getName()))
                        .map(chatMessage -> {
                            if (!chatMessage.getFrom().getId().equals(user.getId())) {
                                return chatMessageService.update(chatMessage.setChatRoom(chatRoom).setHasBeenRead(true));
                            }
                            return chatMessage;
                        }).peek(chatMessage -> LOGGER.info(chatMessage.getFrom().getName()))
                        .collect(toList()))), ChatRoomDto.class))
                .setCount(count);
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null && id >= 0) {
            ChatRoom chatRoom = chatRoomRepository.findOne(id);
            if (chatRoom != null) {
                chatRoomRepository.delete(chatRoom);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    @Override
    public ChatRoom findOne(Long id) {
        checkId(id);
        return sortByDate(chatRoomRepository.findOne(id));
    }

    @Override
    public List<ChatRoom> findAllByUserId(Long id) {
        checkId(id);
//        id = chatUserWrapperService.findByUserId(id).getId();
        return chatRoomRepository.findAllByUserId(id).stream()
                .map(chatRoom -> sortByDate(chatRoom).setMessages(
                        chatRoom.getMessages().stream()
                                .filter(chatMessage ->
                                        chatMessage.equals(chatRoom.getMessages().get(chatRoom.getMessages().size() - 1))
                                ).collect(toList())
                        )
                ).collect(toList());
    }

    @Override
    public ChatRoom sendUserCreateChatRoom(ChatRoom chatRoom) {
        chatRoom.getUsers().stream().map(chatUserWrapper -> chatUserWrapper.getUser()).forEach(user -> {
            this.template.convertAndSend("/chat.init-chat-room" + user.getId(), map(chatRoom, ChatRoomDto.class));
        });
        return chatRoom;
    }

    @Override
    public ChatRoom createOrFindChatRoom(Long userId, Principal principal) throws NullPointerException {
        ChatUserWrapper chatUserWrapper = chatUserWrapperService.findByUserId(userService.findByEmail(principal.getName()).getId());
        if (chatUserWrapper == null)
            chatUserWrapper = chatUserWrapperService.save(new ChatUserWrapper().setUser(userService.findByEmail(principal.getName())));
        ChatUserWrapper chatUserWrapper1 = chatUserWrapperService.findByUserId(userId);
        if (chatUserWrapper1 == null) {
            chatUserWrapper1 = chatUserWrapperService.save(new ChatUserWrapper().setUser(userService.findOne(userId)));
            return save(new ChatRoom()
                    .setUsers(Arrays.asList(chatUserWrapper, chatUserWrapper1)).setAvailable(true));
        }
        LOGGER.info("----------------------------------------------Create or find chat room------------------------------------");
        if (chatUserWrapper.getChatRooms() != null)
            for (ChatRoom chatRoom : chatUserWrapper.getChatRooms()) {
                List<ChatUserWrapper> chatUserWrappers = chatRoom.getUsers();
                if (chatUserWrappers.contains(chatUserWrapper1)) {
//                LOGGER.info(chatRoom);
                    return chatRoom;
                }
            }
        LOGGER.info("------------------------END----------------------Create or find chat room---------------------END---------------");
        return sendUserCreateChatRoom(save(new ChatRoom()
                .setUsers(Arrays.asList(chatUserWrapper, chatUserWrapper1)).setAvailable(true)));
    }
}
