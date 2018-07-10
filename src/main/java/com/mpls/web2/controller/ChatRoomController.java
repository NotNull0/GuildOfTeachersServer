package com.mpls.web2.controller;

import com.mpls.web2.dto.ChatRoomDto;
import com.mpls.web2.dto.ChatRoomIntegerWrapper;
import com.mpls.web2.model.ChatRoom;
import com.mpls.web2.service.ChatRoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/chat-room")
public class ChatRoomController {

    private static final Logger LOGGER = Logger.getLogger(ChatRoomController.class);

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("/find-one-with-messages-pageable/{chatRoomId}/{count}")
    private ResponseEntity<ChatRoomIntegerWrapper> findOneWithMessagesPageable(@PathVariable Long chatRoomId,
                                                                               @PathVariable Integer count,
                                                                               Principal principal) {
        return ResponseEntity.ok(chatRoomService.findOneWithMessagesPageable(chatRoomId, principal, count));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ChatRoomDto> findOneAvailable(@PathVariable Long id) {
        ChatRoom chatRoom = chatRoomService.findOneAvailable(id);
        if (chatRoom == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(chatRoom, ChatRoomDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ChatRoomDto>> findAllAvailable() {
        return ResponseEntity.ok(chatRoomService.findAllAvailable().stream().map(chatRoom -> map(chatRoom, ChatRoomDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<ChatRoomDto>> findAll() {
        return ResponseEntity.ok(chatRoomService.findAll().stream().map(chatRoom -> map(chatRoom, ChatRoomDto.class)).collect(toList()));
    }

    @PostMapping("/save")
    private ResponseEntity<ChatRoomDto> save(@RequestBody ChatRoomDto chatRoomDto) {
        return ResponseEntity.ok(map(chatRoomService.save(map(chatRoomDto, ChatRoom.class)), ChatRoomDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<ChatRoomIntegerWrapper> update(@RequestBody ChatRoomDto chatRoomDto, Principal principal) {
        LOGGER.info("----------------------------Update ChatRoom-------------------------------");
//        LOGGER.info(chatRoomService.update(map(chatRoomDto, ChatRoom.class), principal));
        LOGGER.info("-------------END---------------Update ChatRoom----------------END---------------");
        return ResponseEntity.ok(chatRoomService.update(map(chatRoomDto, ChatRoom.class), principal));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(chatRoomService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-one{id}")
    private ResponseEntity<ChatRoomDto> findOne(@PathVariable Long id) {
        ChatRoom chatRoom;
        if ((chatRoom = chatRoomService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(chatRoom, ChatRoomDto.class));
    }

    @GetMapping("/find-all-by-user-id/{id}")
    private ResponseEntity<List<ChatRoomDto>> findAllByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(chatRoomService.findAllByUserId(id).stream().map(chatRoom ->
                map(chatRoom, ChatRoomDto.class)).collect(toList()));
    }

    @GetMapping("/create-or-find-chat-room/{id}")
    private ResponseEntity<ChatRoomDto> createOrFindChatRoom(@PathVariable Long id, Principal principal) {
        ChatRoom chatRoom = chatRoomService.createOrFindChatRoom(id, principal);
//        LOGGER.info(chatRoom);
        return ResponseEntity.ok(map(chatRoom, ChatRoomDto.class));//todo
    }


}
