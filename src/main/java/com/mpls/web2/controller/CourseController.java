package com.mpls.web2.controller;

import com.mpls.web2.dto.CourseFullDto;
import com.mpls.web2.dto.CoursePageableWrapper;
import com.mpls.web2.dto.CourseShortDto;
import com.mpls.web2.model.Course;
import com.mpls.web2.model.enums.CourseType;
import com.mpls.web2.service.CourseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/course")
public class CourseController {

    private static final Logger LOGGER = Logger.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @GetMapping("/find-all-pageable/{page}/{count}")
    private ResponseEntity<List<CourseFullDto>> findAllPageable(@PathVariable Integer page, @PathVariable Integer count) {
        return ResponseEntity.ok(courseService.findAll(new PageRequest(page, count)).stream().map(course -> map(course, CourseFullDto.class)).collect(toList()));
    }


    @GetMapping("/find-top-three")
    private ResponseEntity<List<CourseFullDto>> findTopThree() {
        return new ResponseEntity<List<CourseFullDto>>(
                courseService.findTop3().stream().map(article -> map(article, CourseFullDto.class)).collect(toList()), HttpStatus.OK);
    }


    @GetMapping("/find-all-pageable-available/{page}/{count}")
    private ResponseEntity<List<CourseFullDto>> findAllPageableAvailable(@PathVariable Integer page, @PathVariable Integer count) {
        List<Course> courses = courseService.findAllAvailable(new PageRequest(page, count));
        return ResponseEntity.ok(courses.stream().map(course -> map(course, CourseFullDto.class)).collect(toList()));
    }

    @GetMapping("/find-by-header/{header}")
    private ResponseEntity<CourseFullDto> findByHeader(@PathVariable String header) {
        Course course;
        if ((course = courseService.findByHeader(header)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(course, CourseFullDto.class));
    }

    @GetMapping("/find-by-description/{description}")
    private ResponseEntity<CourseFullDto> findByDescription(@PathVariable String description) {
        Course course;
        if ((course = courseService.findByDescription(description)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(course, CourseFullDto.class));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<CourseFullDto> findOneAvailable(@PathVariable Long id, Principal principal) {
        Course course;
        if ((course = courseService.findOneAvailable(id, principal)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(course, CourseFullDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<CourseFullDto> findOne(@PathVariable Long id, Principal principal) {
        Course course;
        if ((course = courseService.findOne(id, principal)) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(map(course, CourseFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<CourseFullDto> save(@RequestParam String course, @RequestParam MultipartFile file) {
        return ResponseEntity.ok(map(courseService.save(course, file), CourseFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<CourseFullDto> update(@RequestParam String course, @RequestParam(required = false) MultipartFile file) {
        Course course1 = courseService.update(course);
        if(file != null)
            course1 = courseService.updateImage(course1.getId(), file);
        return ResponseEntity.ok(map(course1, CourseFullDto.class));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<CourseFullDto>> findAll() {
        return ResponseEntity.ok(courseService.findAll().stream().map(course -> map(course, CourseFullDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<CourseFullDto>> findAllAvailable() {
        return ResponseEntity.ok(courseService.findAllAvailable().stream().map(course -> map(course, CourseFullDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-by-course-type")
    private ResponseEntity<List<CourseFullDto>> findAllByCourseType(@RequestBody CourseType courseType) {
        return ResponseEntity.ok(courseService.findAll().stream().map(course -> map(course, CourseFullDto.class)).collect(toList()));
    }

    @GetMapping("/find-all-by-category-id/{id}")
    private ResponseEntity<List<CourseFullDto>> findAllByCourseCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findAllByCourseCategoryId(id).stream().map(course -> map(course, CourseFullDto.class)).collect(toList()));
    }

    @PostMapping("/vote/{courseId}/{rating}")
    private ResponseEntity<CourseFullDto> vote(@PathVariable Long courseId, @PathVariable Integer rating, Principal principal) {
        return ResponseEntity.ok(map(courseService.vote(courseId, rating, principal), CourseFullDto.class));
    }

    @GetMapping("/find-all-by-course-category-and-type/{name}/{typeNumber}/{page}/{count}")
    private ResponseEntity<CoursePageableWrapper> findAllByCourseCategoryAndType(@PathVariable String name,
                                                                                 @PathVariable Integer typeNumber,
                                                                                 @PathVariable Integer page,
                                                                                 @PathVariable Integer count) {
        return ResponseEntity.ok(courseService.findAllByCourseCategoryIdAndType(name, typeNumber, new PageRequest(page, count)));
    }
}
