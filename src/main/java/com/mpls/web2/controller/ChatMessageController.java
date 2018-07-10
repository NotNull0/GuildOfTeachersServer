package com.mpls.web2.controller;

import com.mpls.web2.dto.ChatMessageDto;
import com.mpls.web2.model.ChatMessage;
import com.mpls.web2.repository.ChatMessageRepository;
import com.mpls.web2.service.ChatMessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.UTF_32;

import java.security.Principal;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/chat-message")
public class ChatMessageController {

    private static final Logger LOGGER = Logger.getLogger(ChatMessageController.class);

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ChatMessageDto> findOneAvailable(@PathVariable Long id) {
        ChatMessage chatMessage = chatMessageService.findOneAvailable(id);
        return ResponseEntity.ok(map(chatMessage, ChatMessageDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<ChatMessageDto> findOne(@PathVariable Long id) {
        ChatMessage chatMessage = chatMessageService.findOne(id);
        if (chatMessage == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(chatMessage, ChatMessageDto.class));
    }


    @PostMapping("/save")
    private ResponseEntity<ChatMessageDto> save(@RequestBody ChatMessageDto chatMessageDto) {
        return ResponseEntity.ok(map(chatMessageService.save(map(chatMessageDto, ChatMessage.class)), ChatMessageDto.class));
    }

    @PutMapping("/update")
    private ResponseEntity<ChatMessageDto> update(@RequestBody ChatMessageDto chatMessageDto) {
        return ResponseEntity.ok(map(chatMessageService.update(map(chatMessageDto, ChatMessage.class)), ChatMessageDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.status(chatMessageService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-all-by-from-id/{id}")
    private ResponseEntity<List<ChatMessageDto>> findAllByFromId(@PathVariable Long id) {
        return ResponseEntity.ok(chatMessageService.findAllByFromId(id).stream().map(chatMessage -> map(chatMessage, ChatMessageDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ChatMessageDto>> findAllAvailable() {
        return ResponseEntity.ok(chatMessageService.findAllAvailable().stream().map(chatMessage -> map(chatMessage, ChatMessageDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<ChatMessageDto>> findAll() {
        return ResponseEntity.ok(chatMessageService.findAll().stream().map(chatMessage -> map(chatMessage, ChatMessageDto.class)).collect(toList()));
    }

    @PostMapping("/send/{id}")
    private ResponseEntity<ChatMessageDto> send(@RequestBody ChatMessageDto chatMessageDto, @PathVariable Long id, Principal principal) {
//        try {
        LOGGER.info("-------------------------------------------------------");
        LOGGER.info(chatMessageDto.getText());
        LOGGER.info("-------------------------------------------------------");
        return ResponseEntity.ok(map(chatMessageService.sendChatMessage(id, map(chatMessageDto, ChatMessage.class), principal), ChatMessageDto.class));
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
    }

    @GetMapping("/find-all-by-from-id-and-read/{id}/{read}")
    private ResponseEntity<ChatMessageDto> findAllByFromIdAndRead(@PathVariable Long id, @PathVariable Boolean read) {
        return ResponseEntity.ok(map(chatMessageService.findAllByFromIdAndRead(id, read), ChatMessageDto.class));
    }

    @GetMapping("/count-all-unread-by-chat-room/{chatRoomId}")
    private ResponseEntity<Integer> findAllUnreadByChatRoom(@PathVariable Long chatRoomId, Principal principal) {
        return ResponseEntity.ok(chatMessageService.countAllUnreadByChatRoom(chatRoomId, principal));
    }

    @GetMapping("/count-all-unread")
    private ResponseEntity<Integer> findAllUnread(Principal principal) {
        if(principal == null || principal.getName()==null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(chatMessageService.countAllUnread(principal));
    }

    @PostMapping("/send-smiles")
    private ResponseEntity<String> sendSmiles(@RequestBody ChatMessage chatMessage){
        return ResponseEntity.ok(chatMessageRepository.save(chatMessage.setText(new String(chatMessage.getText().getBytes(),new UTF_32()))).getText());
    }
}
