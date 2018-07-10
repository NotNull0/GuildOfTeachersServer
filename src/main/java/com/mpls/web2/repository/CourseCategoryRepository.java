package com.mpls.web2.repository;

import com.mpls.web2.model.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long>{

    CourseCategory findByName(String name);

    CourseCategory findByAvailableAndId(Boolean available, Long id);

    List<CourseCategory> findAllByAvailable(Boolean available);

    List<CourseCategory> findAllByQuestionnaire_Id(Long id);
}
