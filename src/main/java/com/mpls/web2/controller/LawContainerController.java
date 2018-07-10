package com.mpls.web2.controller;

import com.mpls.web2.dto.LawContainerFullDto;
import com.mpls.web2.dto.LawContainerShortDto;
import com.mpls.web2.model.LawContainer;
import com.mpls.web2.service.LawContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/law-container")
public class LawContainerController {

    @Autowired
    private LawContainerService lawContainerService;

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<LawContainerFullDto> findOneAvailable(@PathVariable Long id) {
        LawContainer lawContainer;
        if ((lawContainer = lawContainerService.findOneAvailable(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(lawContainer, LawContainerFullDto.class));
    }

    @GetMapping("/find-by-header/{name}")
    private ResponseEntity<LawContainerFullDto> findByName(@PathVariable String name) {
        LawContainer lawContainer;
        if ((lawContainer = lawContainerService.findByName(name)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(lawContainer, LawContainerFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<LawContainerFullDto> save(@RequestBody LawContainerFullDto lawContainerFullDto) {
        return ResponseEntity.ok(map(lawContainerService.save(map(lawContainerFullDto, LawContainer.class)), LawContainerFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<LawContainerShortDto> update(@RequestBody LawContainerShortDto lawContainerShortDto) {
        return ResponseEntity.ok(map(lawContainerService.update(map(lawContainerShortDto, LawContainer.class)), LawContainerShortDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(lawContainerService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<LawContainerFullDto> findOne(@PathVariable Long id) {
        LawContainer lawContainer;
        if ((lawContainer = lawContainerService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(lawContainer, LawContainerFullDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<LawContainerFullDto>> findAllAvailable() {
        return ResponseEntity.ok(lawContainerService.findAllAvailable()
                .stream().map(lawContainer -> map(lawContainer, LawContainerFullDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<LawContainerShortDto>> findAll() {
        return ResponseEntity.ok(lawContainerService.findAll()
                .stream().map(lawContainer -> map(lawContainer, LawContainerShortDto.class)).collect(toList()));
    }


}
