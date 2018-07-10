package com.mpls.web2.controller;

import com.mpls.web2.dto.ChatMessageDto;
import com.mpls.web2.model.ChatMessage;
import com.mpls.web2.model.ChatRoom;
import com.mpls.web2.service.ChatMessageService;
import com.mpls.web2.service.ChatRoomService;
import com.mpls.web2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

import static com.mpls.web2.dto.utils.builder.Builder.map;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private UserService userService;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    //method doesn't work
    @MessageMapping("/send/message/{id}")
    public void onReceiveMessage(@RequestBody ChatMessageDto chatMessageDto, @DestinationVariable("id") Long id, Principal principal) {
        chatMessageService.sendChatMessage(id, map(chatMessageDto, ChatMessage.class), principal);
    }

    @PostMapping("/send/message/{id}")
    public ResponseEntity onReviewMessageController(@RequestBody ChatMessageDto chatMessageDto, @PathVariable("id") Long id, Principal principal) {
        ChatRoom chatRoom = chatRoomService.findOne(id);
        if (chatRoom != null) {
//            if(chatRoom.getUsers().contains(userService.findByEmail(principal.getName())))
            chatMessageService.sendChatMessage(id, map(chatMessageDto, ChatMessage.class), principal);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
