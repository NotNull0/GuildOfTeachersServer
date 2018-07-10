package com.mpls.web2.controller;

import com.mpls.web2.dto.TeachingProgramContainerFullDto;
import com.mpls.web2.dto.TeachingProgramContainerShortDto;
import com.mpls.web2.model.TeachingProgramContainer;
import com.mpls.web2.service.TeachingProgramContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/teaching-program-container")
public class TeachingProgramContainerController {

    @Autowired
    TeachingProgramContainerService teachingProgramContainerService;

    @GetMapping("/find-by-name/{name}")
    private ResponseEntity<TeachingProgramContainerFullDto> findByHeader(@PathVariable String name) {
        TeachingProgramContainer teachingProgramContainer;
        if ((teachingProgramContainer = teachingProgramContainerService.findByName(name)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(teachingProgramContainer, TeachingProgramContainerFullDto.class));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<TeachingProgramContainerShortDto> findOneAvailable(@PathVariable Long id) {
        TeachingProgramContainer teachingProgramContainer;
        if ((teachingProgramContainer = teachingProgramContainerService.findOneAvailable(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(teachingProgramContainer, TeachingProgramContainerShortDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<TeachingProgramContainerFullDto> findOne(@PathVariable Long id) {
        TeachingProgramContainer teachingProgramContainer;
        if ((teachingProgramContainer = teachingProgramContainerService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(teachingProgramContainer, TeachingProgramContainerFullDto.class));
    }


    @GetMapping("/find-all-available")
    private ResponseEntity<List<TeachingProgramContainerFullDto>> findAllAvailable() {
        return ResponseEntity.ok(teachingProgramContainerService.findAllAvailable()
                .stream().map(programContainer -> map(programContainer, TeachingProgramContainerFullDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<TeachingProgramContainerShortDto>> findAll() {
        return ResponseEntity.ok(teachingProgramContainerService.findAll()
                .stream().map(programContainer -> map(programContainer, TeachingProgramContainerShortDto.class)).collect(toList()));
    }

    @PostMapping("/save")
    private ResponseEntity<TeachingProgramContainerShortDto> save(@RequestBody TeachingProgramContainerShortDto containerDto) {
        return ResponseEntity.ok(map(teachingProgramContainerService.save(map(containerDto, TeachingProgramContainer.class)), TeachingProgramContainerShortDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<TeachingProgramContainerShortDto> update(@RequestBody TeachingProgramContainerShortDto containerDto) {
        return ResponseEntity.ok(map(teachingProgramContainerService.update(map(containerDto, TeachingProgramContainer.class)), TeachingProgramContainerShortDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(teachingProgramContainerService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

}
