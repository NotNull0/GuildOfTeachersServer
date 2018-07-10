package com.mpls.web2.controller;

import com.mpls.web2.dto.ForumMessageDto;
import com.mpls.web2.model.ForumMessage;
import com.mpls.web2.service.ForumMessageService;
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
@RequestMapping("/forum-message")
public class ForumMessageController {

    @Autowired
    private ForumMessageService forumMessageService;

    @GetMapping("/find-all-pageable-by-topic/{page}/{count}/{id}")
    private ResponseEntity<List<ForumMessageDto>> findAllByForumTopicId(@PathVariable Integer page,
                                                                        @PathVariable Integer count,
                                                                        @PathVariable Long id) {
        return ResponseEntity.ok(forumMessageService.findAllByForumTopicId(new PageRequest(page, count), id).stream().map(forumMessage -> map(forumMessage, ForumMessageDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-pageable/{page}/{count}")
    private ResponseEntity<List<ForumMessageDto>> findAllPageable(@PathVariable Integer page, @PathVariable Integer count) {
        List<ForumMessage> forumMessages = forumMessageService.findAll(new PageRequest(page, count));
        return ResponseEntity.ok(forumMessages.stream().map(forumMessage -> map(forumMessage, ForumMessageDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-by-from-id/{id}")
    private ResponseEntity<List<ForumMessageDto>> findAllByFromId(@PathVariable Long id) {
        return ResponseEntity.ok(forumMessageService.findAllByFromId(id)
                .stream().map(forumMessage -> map(forumMessage, ForumMessageDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ForumMessageDto>> findAllAvailable() {
        return ResponseEntity.ok(forumMessageService.findAllAvailable()
                .stream().map(forumMessage -> map(forumMessage, ForumMessageDto.class)).collect(toList()));
    }


    @GetMapping("/find-all")
    private ResponseEntity<List<ForumMessageDto>> findAll() {
        return ResponseEntity.ok(forumMessageService.findAll()
                .stream().map(forumMessage -> map(forumMessage, ForumMessageDto.class)).collect(toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ForumMessageDto> findOneAvailable(@PathVariable Long id) {
        ForumMessage forumMessage;
        if ((forumMessage = forumMessageService.findOneAvailable(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumMessage, ForumMessageDto.class));
    }


    @GetMapping("/find-one/{id}")
    private ResponseEntity<ForumMessageDto> findOne(@PathVariable Long id) {
        ForumMessage forumMessage;
        if ((forumMessage = forumMessageService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumMessage, ForumMessageDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<ForumMessageDto> save(@RequestBody ForumMessageDto forumMessageDto) {
        return ResponseEntity.ok(map(forumMessageService.save(map(forumMessageDto, ForumMessage.class)), ForumMessageDto.class));
    }

    @PostMapping("/save/{id}")
    private ResponseEntity<ForumMessageDto> save(@RequestBody ForumMessageDto forumMessageDto, @PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(map(forumMessageService.save(map(forumMessageDto, ForumMessage.class), principal, id), ForumMessageDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<ForumMessageDto> update(@RequestBody ForumMessageDto forumMessageDto) {
        return ResponseEntity.ok(map(forumMessageService.update(map(forumMessageDto, ForumMessage.class)), ForumMessageDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(forumMessageService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }


}
