package com.mpls.web2.controller;

import com.mpls.web2.dto.TeachingProgramFullDto;
import com.mpls.web2.dto.TeachingProgramShortDto;
import com.mpls.web2.model.TeachingProgram;
import com.mpls.web2.service.TeachingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("teaching-program")
public class TeachingProgramController {

    @Autowired
    private TeachingProgramService programService;

    @GetMapping("/find-all-by-container/{id}")
    private ResponseEntity<List<TeachingProgramShortDto>> findAllByContainer(@PathVariable Long id) {
        return ResponseEntity.ok(programService.findAllByContainerId(id)
                .stream().map(teachingProgram -> map(teachingProgram, TeachingProgramShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<TeachingProgramShortDto>> findAllAvailable() {
        return ResponseEntity.ok(programService.findAllAvailable()
                .stream().map(teachingProgram -> map(teachingProgram, TeachingProgramShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<TeachingProgramShortDto>> findAll() {
        return ResponseEntity.ok(programService.findAll()
                .stream().map(teachingProgram -> map(teachingProgram, TeachingProgramShortDto.class)).collect(toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<TeachingProgramFullDto> findOneAvailable(@PathVariable Long id) {
        TeachingProgram teachingProgram;
        if ((teachingProgram = programService.findOneAvailable(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(teachingProgram, TeachingProgramFullDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<TeachingProgramFullDto> findOne(@PathVariable Long id) {
        TeachingProgram teachingProgram;
        if ((teachingProgram = programService.findOne(id)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(teachingProgram, TeachingProgramFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<TeachingProgramFullDto> save(@RequestParam String teachingProgramJson, @RequestParam(required = false) MultipartFile multipartFile) {
        return ResponseEntity.ok(map(programService.save(teachingProgramJson, multipartFile), TeachingProgramFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<TeachingProgramFullDto> update(@RequestParam String teachingProgramJson, @RequestParam(required = false) MultipartFile file) {
        TeachingProgram teachingProgram = programService.update(teachingProgramJson);
        if (file != null) {
            teachingProgram = programService.updateFile(teachingProgram.getId(), file);
        }
        return ResponseEntity.ok(map(teachingProgram, TeachingProgramFullDto.class));
    }


    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(programService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }


}
