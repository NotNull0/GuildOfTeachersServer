package com.mpls.web2.controller;

import com.mpls.web2.dto.ArticleFullDto;
import com.mpls.web2.dto.ArticlePagebleWrapper;
import com.mpls.web2.dto.ArticleShortDto;
import com.mpls.web2.dto.UserShortDto;
import com.mpls.web2.model.Article;
import com.mpls.web2.model.User;
import com.mpls.web2.service.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final Logger LOGGER = Logger.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping("/find-top-five")
    private ResponseEntity<List<ArticleFullDto>> findTopFive() {
        return new ResponseEntity<List<ArticleFullDto>>(
                articleService.findTop5().stream().map(article -> map(article, ArticleFullDto.class)).collect(toList()), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<ArticleFullDto> save(@RequestParam String article, @RequestParam(required = false) MultipartFile image) {
        return new ResponseEntity<ArticleFullDto>(
                map(articleService.save(article, image), ArticleFullDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/find-all-pageable/{page}/{count}")
    private ResponseEntity<List<ArticleFullDto>> findAllPageable(@PathVariable Integer page, @PathVariable Integer count) {
        return ResponseEntity.ok(articleService.findAll(new PageRequest(page, count)).stream().map(article -> map(article, ArticleFullDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-pageable-available/{page}/{count}")
    private ResponseEntity<ArticlePagebleWrapper> findAllPageableAvailable(@PathVariable Integer page, @PathVariable Integer count) {
        return ResponseEntity.ok(articleService.findAllByAvailable(new PageRequest(page, count,new Sort(new Sort.Order(Sort.Direction.DESC,"datetime")))));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<ArticleFullDto>> findAll() {
        return new ResponseEntity<List<ArticleFullDto>>(
                articleService.findAll().stream().map(article -> map(article, ArticleFullDto.class)).collect(toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ArticleFullDto>> findAllAvailable() {
        return new ResponseEntity<List<ArticleFullDto>>(
                articleService.findAllAvailable().stream().map(article -> map(article, ArticleFullDto.class)).collect(toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<ArticleFullDto> findOne(@PathVariable Long id) {
        Article article = articleService.findOne(id);
        return (article != null) ? new ResponseEntity<ArticleFullDto>(map(article, ArticleFullDto.class), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update")
    private ResponseEntity<ArticleFullDto> update(@RequestParam String articleJson, @RequestParam(required = false) MultipartFile image) {
        Article article = articleService.update(articleJson);
        if(image != null){
            article = articleService.updateImage(article.getId(), image);
        }
        return ResponseEntity.ok(map(article, ArticleFullDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity(articleService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ArticleFullDto> findOneAvailable(@PathVariable Long id) {
        Article article = articleService.findOneAvailable(id);
        if (article == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<ArticleFullDto>(map(article, ArticleFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-by-header")
    private ResponseEntity<ArticleShortDto> findByHeader(@RequestParam String header) {
        Article article = articleService.findByHeader(header);
        if (article == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<ArticleShortDto>(map(article, ArticleShortDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-by-text")
    private ResponseEntity<ArticleShortDto> findByText(@RequestParam String text) {
        Article article = articleService.findByText(text);
        if (article == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<ArticleShortDto>(map(article, ArticleShortDto.class), HttpStatus.OK);
    }

}
