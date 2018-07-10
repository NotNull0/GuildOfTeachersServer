package com.mpls.web2.controller;

import com.mpls.web2.dto.SpecializationFullDto;
import com.mpls.web2.model.Specialization;
import com.mpls.web2.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/specialization")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @GetMapping("/find-by-name/{name}")
    private ResponseEntity<SpecializationFullDto> findByHeader(@PathVariable String name) {
        Specialization specialization;
        if ((specialization = specializationService.findByName(name)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(specialization, SpecializationFullDto.class));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<SpecializationFullDto> findOneAvailable(@PathVariable Long id) {
        Specialization specialization;
        if ((specialization = specializationService.findOneAvailable(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(specialization, SpecializationFullDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<SpecializationFullDto> findOne(@PathVariable Long id) {
        Specialization specialization;
        if ((specialization = specializationService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(specialization, SpecializationFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<SpecializationFullDto> save(@RequestBody SpecializationFullDto specializationFullDto) {
        return ResponseEntity.ok(map(specializationService.save(map(specializationFullDto, Specialization.class)), SpecializationFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<SpecializationFullDto> update(@RequestBody SpecializationFullDto specializationFullDto) {
        return ResponseEntity.ok(map(specializationService.save(map(specializationFullDto, Specialization.class)), SpecializationFullDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(specializationService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }


    @GetMapping("/find-all-available")
    private ResponseEntity<List<SpecializationFullDto>> findAllAvailable() {
        return ResponseEntity.ok(specializationService.findAllAvailable()
                .stream().map(specialization -> map(specialization, SpecializationFullDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<SpecializationFullDto>> findAll() {
        return ResponseEntity.ok(specializationService.findAll()
                .stream().map(specialization -> map(specialization, SpecializationFullDto.class)).collect(toList()));
    }

}
