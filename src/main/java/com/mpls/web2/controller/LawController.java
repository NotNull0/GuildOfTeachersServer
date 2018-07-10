package com.mpls.web2.controller;

import com.mpls.web2.dto.LawFullDto;
import com.mpls.web2.dto.LawShortDto;
import com.mpls.web2.model.Law;
import com.mpls.web2.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/law")
public class LawController {

    @Autowired
    private LawService lawService;

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<LawFullDto> findOneAvailable(@PathVariable Long id) {
        Law law;
        if ((law = lawService.findOneAvailable(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(law, LawFullDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<LawFullDto> findOne(@PathVariable Long id) {
        Law law;
        if ((law = lawService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(law, LawFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<LawShortDto> save(@RequestBody LawShortDto lawShortDto) {
        return ResponseEntity.ok(map(lawService.save(map(lawShortDto, Law.class)), LawShortDto.class));
    }

    @PostMapping("/save/{id}")
    private ResponseEntity<LawShortDto> saveWithContainer(@RequestBody LawShortDto lawShortDto, @PathVariable Long id) {
        return ResponseEntity.ok(map(lawService.save(map(lawShortDto, Law.class), id), LawShortDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<LawFullDto> update(@RequestBody Law lawDto) {
        return ResponseEntity.ok(map(lawService.update(lawDto), LawFullDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(lawService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<LawShortDto>> findAll() {
        return ResponseEntity.ok(lawService.findAll()
                .stream().map(law -> map(law, LawShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-sort")
    private ResponseEntity<List<LawShortDto>> findAllSort() {
        return ResponseEntity.ok(lawService.findAll()
                .stream().map(law -> map(law, LawShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<LawShortDto>> findAllAvailable() {
        return ResponseEntity.ok(lawService.findAllAvailable()
                .stream().map(law -> map(law, LawShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-by-container/{id}")
    private ResponseEntity<List<LawShortDto>> findAllByContainerId(@PathVariable Long id) {
        return ResponseEntity.ok(lawService.findAllByContainerId(id)
                .stream().map(law -> map(law, LawShortDto.class)).collect(toList()));
    }

}
