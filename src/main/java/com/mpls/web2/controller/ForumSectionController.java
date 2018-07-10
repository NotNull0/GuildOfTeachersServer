package com.mpls.web2.controller;

import com.mpls.web2.dto.ForumSectionFullDto;
import com.mpls.web2.dto.ForumSectionShortDto;
import com.mpls.web2.model.ForumSection;
import com.mpls.web2.service.ForumSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/forum-section")
public class ForumSectionController {

    @Autowired
    private ForumSectionService forumSectionService;

    @GetMapping("/find-all-pageable/{page}/{count}")
    private ResponseEntity<List<ForumSectionShortDto>> findAllPageable(@PathVariable Integer page, @PathVariable Integer count) {
        List<ForumSection> forumSections = forumSectionService.findAll(new PageRequest(page, count)).getContent();
        return ResponseEntity.ok(forumSections.stream().map(forumSection -> map(forumSection, ForumSectionShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-by-header/{header}")
    private ResponseEntity<ForumSectionFullDto> findByHeader(@PathVariable String header) {
        ForumSection forumSection;
        if ((forumSection = forumSectionService.findByHeader(header)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumSection, ForumSectionFullDto.class));
    }

    @GetMapping("/find-by-description/{description}")
    private ResponseEntity<ForumSectionFullDto> findByDescription(@PathVariable String description) {
        ForumSection forumSection;
        if ((forumSection = forumSectionService.findByDescription(description)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumSection, ForumSectionFullDto.class));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ForumSectionFullDto> findOneAvailable(@PathVariable Long id) {
        ForumSection forumSection;
        if ((forumSection = forumSectionService.findOneAvailable(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumSection, ForumSectionFullDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<ForumSectionFullDto> findOne(@PathVariable Long id) {
        ForumSection forumSection;
        if ((forumSection = forumSectionService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(forumSection, ForumSectionFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<ForumSectionShortDto> save(@RequestBody ForumSectionShortDto forumSectionContainer) {
        return ResponseEntity.ok(map(forumSectionService.save(map(forumSectionContainer, ForumSection.class)), ForumSectionShortDto.class));
    }

    @PostMapping("/save/{id}")
    private ResponseEntity<ForumSectionShortDto> saveWithContainer(@RequestBody ForumSectionShortDto forumSectionContainer, @PathVariable Long id) {
        return ResponseEntity.ok(map(forumSectionService.save(map(forumSectionContainer, ForumSection.class), id), ForumSectionShortDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<ForumSectionFullDto> updateWithContainer(@RequestBody ForumSectionFullDto forumSectionFullDto) {
        return ResponseEntity.ok(map(forumSectionService.update(map(forumSectionFullDto, ForumSection.class)), ForumSectionFullDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(forumSectionService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<ForumSectionShortDto>> findAll() {
        return ResponseEntity.ok(forumSectionService.findAllAvailable()
                .stream().map(forumSection -> map(forumSection, ForumSectionShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-by-container-id/{id}")
    private ResponseEntity<List<ForumSectionShortDto>> findAllByContainerId(@PathVariable Long id) {
        return ResponseEntity.ok(forumSectionService.findAllByContainerId(id)
                .stream().map(forumSection -> map(forumSection, ForumSectionShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ForumSectionShortDto>> findAllAvailable() {
        return ResponseEntity.ok(forumSectionService.findAllAvailable().stream()
                .map(forumSection -> map(forumSection, ForumSectionShortDto.class)).collect(toList()));
    }

}
