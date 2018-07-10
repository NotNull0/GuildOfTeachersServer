package com.mpls.web2.service.impl;

import com.mpls.web2.dto.ChatMessageDto;
import com.mpls.web2.model.ChatMessage;
import com.mpls.web2.model.ChatRoom;
import com.mpls.web2.model.ChatUserWrapper;
import com.mpls.web2.repository.ChatMessageRepository;
import com.mpls.web2.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static com.mpls.web2.service.exceptions.Validation.checkId;
import static java.util.stream.Collectors.toList;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    private static final Logger LOGGER = Logger.getLogger(ChatMessageServiceImpl.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatUserWrapperService chatUserWrapperService;

    @Autowired
    private FileService fileService;

    private List<ChatMessage> sortByDate(List<ChatMessage> chatMessages) {
        if (chatMessages.size() == 0) {
            return chatMessages;
        }
        chatMessages.sort((p1, p2) -> p1.getDatetime().compareTo(p2.getDatetime()));
        return chatMessages;
    }

    @Override
    public List<ChatMessage> findAllByChatRoomIdAndChangeRead(Long chatRoomId, Principal principal, Integer count) {
        List<ChatMessage> chatMessages = chatMessageRepository.findAllByChatRoom_Id(chatRoomId).stream()
                .map(chatMessage -> {
                    if (!chatMessage.getFrom().getId().equals(userService.findByEmail(principal.getName()).getId())) {
                        update(chatMessage.setChatRoom(chatRoomService.findOne(chatRoomId)).setHasBeenRead(true));
                    }
                    return chatMessage;
                }).collect(toList());
        if (chatMessages.size() < count)
            count = chatMessages.size();
        Collections.reverse(chatMessages);
        return new ArrayList<ChatMessage>(chatMessages.subList(0, count));

    }

    @Override
    public ChatMessage findOneAvailable(Long id) {
        checkId(id);
        return chatMessageRepository.findByAvailableAndId(true, id);
    }

    @Override
    public ChatMessage findOne(Long id) {
        checkId(id);
        return chatMessageRepository.findOne(id);
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage
                .setDatetime(Timestamp.valueOf(LocalDateTime.now()))
                .setHasBeenRead(false));
    }

    @Override
    public ChatMessage update(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            ChatMessage chatMessage = chatMessageRepository.findOne(id);
            if (chatMessage != null) {
                chatMessageRepository.delete(chatMessage);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public List<ChatMessage> findAllByFromId(Long id) {
        checkId(id);
        return chatMessageRepository.findAllByFrom_Id(id);
    }

    @Override
    public List<ChatMessage> findAllAvailable() {
        return chatMessageRepository.findAllByAvailable(true);
    }

    @Override
    public List<ChatMessage> findAll() {
        return chatMessageRepository.findAll();
    }

    @Override
    public List<ChatMessage> findAllByFromIdAndRead(Long id, Boolean read) {
        checkId(id);
        return chatMessageRepository.findAllByFrom_IdAndHasBeenRead(id, read);
    }

    @Override
    public ChatMessage sendChatMessage(Long chatRoomId, ChatMessage chatMessage, Principal principal) {
        chatMessage.setDatetime(Timestamp.valueOf(LocalDateTime.now()));
        chatMessage.setFrom(userService.findByEmail(principal.getName()));
        chatMessage.setChatRoom(chatRoomService.findOne(chatRoomId));
        this.template.convertAndSend("/chat." + chatRoomId, map(chatMessageRepository.save(chatMessage), ChatMessageDto.class));
        ChatMessage chatMessage1 = save(chatMessage);
        chatMessage.getFiles().stream().forEach(file -> fileService.update(fileService.findOne(file.getId()).setChatMessage(chatMessage1)));
        return chatMessage1;
    }

    @Override
    public Integer countAllUnreadByChatRoom(Long chatRoomId, Principal principal) {
        if (principal == null)
            return 0;
        return chatMessageRepository.countAllByChatRoom_IdAndHasBeenReadAndFromNot(chatRoomId, false,
                userService.findByEmail(principal.getName()));
    }

    @Override
    public Integer countAllUnread(Principal principal) {
        Integer count = 0;
        if (principal == null || principal.getName() == null)
            return count;
        ChatUserWrapper user = chatUserWrapperService.findByUserId(userService.findByEmail(principal.getName()).getId());
        if (user == null)
            return count;
        if (user.getChatRooms() == null || user.getChatRooms().size() == 0)
            return count;
        for (ChatRoom chatRoom : user.getChatRooms()) {
            count += countAllUnreadByChatRoom(chatRoom.getId(), principal);
        }
        return count;
//        return chatMessageRepository.countAllByHasBeenReadAndFromNot(false,
//                userService.findByEmail(principal.getName()));
    }

//    public static String removeBadChars(String s) {
//        if (s == null) return null;
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0 ; i < s.length() ; i++){
//            if (Character.isHighSurrogate(s.charAt(i))) continue;
//            sb.append(s.charAt(i));
//        }
//        return sb.toString();
//    }

}
