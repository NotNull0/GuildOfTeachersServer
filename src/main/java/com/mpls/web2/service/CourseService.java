package com.mpls.web2.service;

import com.mpls.web2.dto.CoursePageableWrapper;
import com.mpls.web2.model.Course;
import com.mpls.web2.model.enums.CourseType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface CourseService {

    Course findByHeader(String header);

    Course findByDescription(String description);

    Course findOneAvailable(Long id, Principal principal);

    Course findOne(Long id, Principal principal);

    Course save(String course, MultipartFile multipartFile);

    Course update(String course);

    Course updateImage(Long courseId, MultipartFile multipartFile);

    Boolean delete(Long id);

    List<Course> findAll();

    List<Course> findAllAvailable();

    List<Course> findAllByCourseType(CourseType courseType);

    List<Course> findAll(Sort sort);

    List<Course> findAllByCourseCategoryId(Long id);

    List<Course> findAll(Pageable pageable);

    List<Course> findAllAvailable(Pageable pageable);

    Course vote(Long courseId, Integer rating, Principal principal);

    CoursePageableWrapper findAllByCourseCategoryIdAndType(String name, Integer typeNumber, Pageable pageable);

    List<Course> findTop3();

}
