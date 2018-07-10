package com.mpls.web2.repository;

import com.mpls.web2.model.Course;
import com.mpls.web2.model.enums.CourseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByHeader(String header);

    Course findByDescription(String description);

    List<Course> findAllByCourseCategory_Id(Long id);

    Course findByAvailableAndId(Boolean available, Long id);

    List<Course> findAllByAvailable(Boolean available);

    List<Course> findAll(Sort sort);

    List<Course> findAll();

    List<Course> findAllByType(CourseType type);

    Page<Course> findAll(Pageable pageable);

    Page<Course> findAllByAvailable(Boolean available, Pageable pageable);

    Page<Course> findAllByCourseCategory_NameAndAvailable(String name, Boolean available, Pageable pageable);

    Page<Course> findAllByTypeAndAvailable(CourseType type, Boolean available, Pageable pageable);

    Page<Course> findAllByCourseCategory_NameAndTypeAndAvailable(String name, CourseType type, Boolean available, Pageable pageable);

    Integer countAllByAvailable(Boolean available);

    Integer countAllByCourseCategory_NameAndAvailable(String courseCategory_name, Boolean available);

    Integer countAllByTypeAndAvailable(CourseType type, Boolean available);

    Integer countAllByCourseCategory_NameAndTypeAndAvailable(String courseCategory_name, CourseType type, Boolean available);

}
