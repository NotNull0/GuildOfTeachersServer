package com.mpls.web2.controller;

import com.mpls.web2.dto.ForumSectionContainerDto;
import com.mpls.web2.model.ForumSectionContainer;
import com.mpls.web2.service.ForumSectionContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/forum-section-container")
public class ForumSectionContainerController {

    @Autowired
    private ForumSectionContainerService forumSectionContainerService;

    @GetMapping("/find-by-header/{header}")
    private ResponseEntity<ForumSectionContainerDto> findByHeader(@PathVariable String header) {
        ForumSectionContainer forumSectionContainer;
        if ((forumSectionContainer = forumSectionContainerService.findByHeader(header)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumSectionContainer, ForumSectionContainerDto.class));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ForumSectionContainerDto> findOneAvailable(@PathVariable Long id) {
        ForumSectionContainer forumSectionContainer;
        if ((forumSectionContainer = forumSectionContainerService.findOneAvailable(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumSectionContainer, ForumSectionContainerDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<ForumSectionContainerDto> findOne(@PathVariable Long id) {
        ForumSectionContainer forumSectionContainer;
        if ((forumSectionContainer = forumSectionContainerService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumSectionContainer, ForumSectionContainerDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<ForumSectionContainerDto> save(@RequestBody ForumSectionContainerDto forumSectionContainer) {
        return ResponseEntity.ok(map(forumSectionContainerService.save(map(forumSectionContainer, ForumSectionContainer.class)), ForumSectionContainerDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<ForumSectionContainerDto> update(@RequestBody ForumSectionContainerDto forumSectionContainer) {
        return ResponseEntity.ok(map(forumSectionContainerService.update(map(forumSectionContainer, ForumSectionContainer.class)), ForumSectionContainerDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(forumSectionContainerService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ForumSectionContainerDto>> findAllAvailable() {
        return ResponseEntity.ok(forumSectionContainerService.findAllAvailable()
                .stream().map(container -> map(container, ForumSectionContainerDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<ForumSectionContainerDto>> findAll() {
        return ResponseEntity.ok(forumSectionContainerService.findAll()
                .stream().map(container -> map(container, ForumSectionContainerDto.class)).collect(toList()));
    }

    @GetMapping("/find-by-forum-section-id/{id}")
    private ResponseEntity<ForumSectionContainerDto> findByForumSectionId(@PathVariable Long id){
        return ResponseEntity.ok(map(forumSectionContainerService.findBySectionId(id), ForumSectionContainerDto.class));
    }

}
