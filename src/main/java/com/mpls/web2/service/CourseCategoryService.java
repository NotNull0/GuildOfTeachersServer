package com.mpls.web2.service;

import com.mpls.web2.model.CourseCategory;

import java.util.List;

public interface CourseCategoryService {

    CourseCategory findByName(String name);

    List<CourseCategory> findAll();

    CourseCategory findOne(Long id);

    CourseCategory save(CourseCategory courseCategory);

    CourseCategory update(CourseCategory courseCategory);

    Boolean delete(Long id);

    CourseCategory findOneAvailable(Long id);

    List<CourseCategory> findAllAvailable();

    List<CourseCategory> findAllByQuestionnaireId(Long id);

}
