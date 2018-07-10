package com.mpls.web2.controller;

import com.mpls.web2.dto.ForumTopicFullDto;
import com.mpls.web2.dto.ForumTopicShortDto;
import com.mpls.web2.model.ForumTopic;
import com.mpls.web2.service.ForumTopicService;
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
@RequestMapping("/forum-topic")
public class ForumTopicController {

    private static final Logger LOGGER = Logger.getLogger(ForumTopicController.class);

    @Autowired
    private ForumTopicService forumTopicService;

    @GetMapping("/find-all-available-forum-topic/{id}/{page}/{count}")
    private ResponseEntity<List<ForumTopicShortDto>> findAllPageable(@PathVariable Long id, @PathVariable Integer page, @PathVariable Integer count) {
        List<ForumTopic> forumTopics = forumTopicService.findAllByAvailableByForumSectionId(new PageRequest(page, count), id);
        return ResponseEntity.ok(forumTopics.stream().map(forumTopic -> map(forumTopic, ForumTopicShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-pageable/{page}/{count}")
    private ResponseEntity<List<ForumTopicShortDto>> findAllPageable(@PathVariable Integer page, @PathVariable Integer count) {
        List<ForumTopic> forumTopics = forumTopicService.findAll(new PageRequest(page, count));
        return ResponseEntity.ok(forumTopics.stream().map(forumTopic -> map(forumTopic, ForumTopicShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-by-header/{header}")
    private ResponseEntity<ForumTopicFullDto> findByHeader(@PathVariable String header) {
        ForumTopic forumTopic;
        if ((forumTopic = forumTopicService.findByHeader(header)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumTopic, ForumTopicFullDto.class));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ForumTopicFullDto> findOneAvailable(@PathVariable Long id) {
        ForumTopic forumTopic;
        LOGGER.info(forumTopicService.findOneAvailable(id).getAuthor());
        if ((forumTopic = forumTopicService.findOneAvailable(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumTopic, ForumTopicFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<ForumTopicShortDto> save(@RequestBody ForumTopicShortDto forumSectionContainer) {
        return ResponseEntity.ok(map(forumTopicService.save(map(forumSectionContainer, ForumTopic.class)), ForumTopicShortDto.class));
    }

    @PostMapping("/save/{id}")
    private ResponseEntity<ForumTopicShortDto> send(@RequestBody ForumTopicShortDto forumSectionContainer, Principal principal, @PathVariable Long id) {
        return ResponseEntity.ok(map(forumTopicService.save(map(forumSectionContainer, ForumTopic.class), principal, id), ForumTopicShortDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<ForumTopicFullDto> update(@RequestBody ForumTopicFullDto forumTopicFullDto) {
        return ResponseEntity.ok(map(forumTopicService.update(map(forumTopicFullDto, ForumTopic.class)), ForumTopicFullDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(forumTopicService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-all-by-author/{id}")
    private ResponseEntity<List<ForumTopicShortDto>> findAllByAuthorId(@PathVariable Long id) {
        return ResponseEntity.ok(forumTopicService.findAllByAuthorId(id)
                .stream().map(forumTopic -> map(forumTopic, ForumTopicShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-by-forum-section/{id}")
    private ResponseEntity<List<ForumTopicFullDto>> findAllByForumSectionId(@PathVariable Long id) {
        return ResponseEntity.ok(forumTopicService.findAllByForumSectionId(id)
                .stream().map(forumTopic -> map(forumTopic, ForumTopicFullDto.class)).collect(toList()));
    }


    @GetMapping("/find-all-sort")
    private ResponseEntity<List<ForumTopicShortDto>> findAllSort() {
        return ResponseEntity.ok(forumTopicService.findAll()
                .stream().map(forumTopic -> map(forumTopic, ForumTopicShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<ForumTopicShortDto>> findAll() {
        return ResponseEntity.ok(forumTopicService.findAll()
                .stream().map(forumTopic -> map(forumTopic, ForumTopicShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ForumTopicShortDto>> findAllAvailable() {
        return ResponseEntity.ok(forumTopicService.findAllAvailable()
                .stream().map(forumTopic -> map(forumTopic, ForumTopicShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-pages/{shteupuk}")
    private ResponseEntity<Integer> findAllPages(@PathVariable Integer shteupuk) {
        return ResponseEntity.ok(forumTopicService.countAllPages(shteupuk));
    }

    @GetMapping("/find-page/{pageNumber}/{count}")
    private ResponseEntity<List<ForumTopicShortDto>> findPAge(@PathVariable Integer pageNumber, @PathVariable Integer count) {
        return ResponseEntity.ok(forumTopicService.findAllByAvailable(pageNumber, count).stream().map(forumTopic -> map(forumTopic, ForumTopicShortDto.class)).collect(toList()));
    }


}
