package com.mpls.web2.controller;

import com.mpls.web2.dto.CourseCategoryDto;
import com.mpls.web2.model.CourseCategory;
import com.mpls.web2.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/course-category")
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    @GetMapping("/find-by-name")
    private ResponseEntity<CourseCategoryDto> findByName(@RequestParam String name) {
        CourseCategory category = courseCategoryService.findByName(name);
        if (category == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(category, CourseCategoryDto.class));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<CourseCategoryDto>> findAll() {
        return ResponseEntity.ok(courseCategoryService.findAll().stream()
                .map(courseCategory -> map(courseCategory, CourseCategoryDto.class)).collect(toList()));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<CourseCategoryDto> findOne(@PathVariable Long id) {
        CourseCategory category = courseCategoryService.findOne(id);
        if (category == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(category, CourseCategoryDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<CourseCategoryDto> save(@RequestBody CourseCategoryDto categoryDto) {
        return ResponseEntity.ok(map(courseCategoryService.save(map(categoryDto, CourseCategory.class)), CourseCategoryDto.class));
    }

    @PutMapping("/update")
    private ResponseEntity<CourseCategoryDto> update(@RequestBody CourseCategoryDto categoryDto) {
        return ResponseEntity.ok(map(courseCategoryService.update(map(categoryDto, CourseCategory.class)), CourseCategoryDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(courseCategoryService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<CourseCategoryDto> findOneAvailable(@PathVariable Long id) {
        CourseCategory category = courseCategoryService.findOneAvailable(id);
        if (category == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(category, CourseCategoryDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<CourseCategoryDto>> findAllAvailable() {
        return ResponseEntity.ok(courseCategoryService.findAllAvailable().stream()
                .map(courseCategory -> map(courseCategory, CourseCategoryDto.class)).collect(toList()));
    }
}
