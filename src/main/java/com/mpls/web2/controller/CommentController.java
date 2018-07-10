package com.mpls.web2.controller;

import com.mpls.web2.dto.CommentDto;
import com.mpls.web2.model.Comment;
import com.mpls.web2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/find-all-by-from-id")
    private ResponseEntity<List<CommentDto>> findAllByFromId(Long id) {
        return ResponseEntity.ok(commentService.findAllByFromId(id)
                .stream().map(comment -> map(comment, CommentDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<CommentDto>> findAllAvailable() {
        return ResponseEntity.ok(commentService.findAllAvailable()
                .stream().map(comment -> map(comment, CommentDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<CommentDto>> findAll() {
        return ResponseEntity.ok(commentService.findAll()
                .stream().map(comment -> map(comment, CommentDto.class)).collect(toList()));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<CommentDto> findOne(@PathVariable Long id) {
        Comment comment = commentService.findOne(id);
        return ResponseEntity.ok(map(comment, CommentDto.class));
    }

    @GetMapping("/find-one-available")
    private ResponseEntity<CommentDto> findOneAvailable(@PathVariable Long id) {
        Comment comment;
        if ((comment = commentService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(comment, CommentDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<CommentDto> save(@RequestBody CommentDto comment) {
        return ResponseEntity.ok(map(commentService.save(map(comment, Comment.class)), CommentDto.class));
    }

    @PostMapping("/save/course/{id}")
    private ResponseEntity<CommentDto> saveCourse(@RequestBody CommentDto comment, @PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(map(commentService.saveCourse(map(comment, Comment.class), id, principal), CommentDto.class));
    }

    @PostMapping("/save/article/{id}")
    private ResponseEntity<CommentDto> saveArticle(@RequestBody CommentDto comment, @PathVariable Long id, Principal principal) {

        return ResponseEntity.ok(map(commentService.saveArticle(map(comment, Comment.class), id, principal), CommentDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<CommentDto> update(@RequestBody CommentDto comment) {
        return ResponseEntity.ok(map(commentService.update(map(comment, Comment.class)), CommentDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(commentService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

}
