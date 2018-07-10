package com.mpls.web2.controller;

import com.mpls.web2.dto.ChatUserWrapperDto;
import com.mpls.web2.model.ChatUserWrapper;
import com.mpls.web2.service.ChatUserWrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/chat-user-wrapper")
public class ChatUserWrapperController {

    @Autowired
    private ChatUserWrapperService chatUserWrapperService;

    @GetMapping("/find-by-user-id/{id}")
    private ResponseEntity<ChatUserWrapperDto> findByUserId(@PathVariable Long id) {
        ChatUserWrapper chatUserWrapper = chatUserWrapperService.findByUserId(id);
        if(chatUserWrapper == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(chatUserWrapper, ChatUserWrapperDto.class));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ChatUserWrapperDto> findOneAvailable(@PathVariable Long id) {
        ChatUserWrapper chatUserWrapper = chatUserWrapperService.findOneAvailable(id);
        if(chatUserWrapper == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(chatUserWrapper, ChatUserWrapperDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<ChatUserWrapperDto> findOne(Long id) {
        ChatUserWrapper chatUserWrapper = chatUserWrapperService.findOne(id);
        if(chatUserWrapper == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(chatUserWrapper, ChatUserWrapperDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ChatUserWrapperDto>> findAllAvailable() {
        return ResponseEntity.ok(chatUserWrapperService.findAllAvailable()
                .stream().map(chatUserWrapper -> map(chatUserWrapper, ChatUserWrapperDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<ChatUserWrapperDto>> findAll() {
        return ResponseEntity.ok(chatUserWrapperService.findAll()
                .stream().map(chatUserWrapper -> map(chatUserWrapper, ChatUserWrapperDto.class)).collect(toList()));
    }

    @PostMapping("/save")
    private ResponseEntity<ChatUserWrapperDto> save(@RequestBody ChatUserWrapperDto chatUserWrapperDto) {
        return ResponseEntity.ok(map(chatUserWrapperService.save(map(chatUserWrapperDto, ChatUserWrapper.class)), ChatUserWrapperDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<ChatUserWrapperDto> update(@RequestBody ChatUserWrapperDto chatUserWrapperDto) {
        return ResponseEntity.ok(map(chatUserWrapperService.update(map(chatUserWrapperDto, ChatUserWrapper.class)), ChatUserWrapperDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(Long id) {
        return ResponseEntity.status(chatUserWrapperService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

}