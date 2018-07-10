package com.mpls.web2.service.impl;

import com.mpls.web2.model.CourseCategory;
import com.mpls.web2.repository.CourseCategoryRepository;
import com.mpls.web2.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class CourseCategoryServiceImpl implements CourseCategoryService{

    @Autowired
    private CourseCategoryRepository courseCategoryRepository;

    @Override
    public CourseCategory findByName(String name) {
        checkString(name);
        return courseCategoryRepository.findByName(name);
    }

    @Override
    public List<CourseCategory> findAll() {
        return courseCategoryRepository.findAll();
    }

    @Override
    public CourseCategory findOne(Long id) {
        checkId(id);
        return courseCategoryRepository.findOne(id);
    }

    @Override
    public CourseCategory save(CourseCategory category) {
        checkSave(category);
        return courseCategoryRepository.save(category.setCount(0.0).setAvailable(true));
    }

    @Override
    public CourseCategory update(CourseCategory category) {
        checkUpdate(category.getId(), courseCategoryRepository);
        return courseCategoryRepository.save(category);
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            CourseCategory category = courseCategoryRepository.findOne(id);
            if (category != null) {
                courseCategoryRepository.delete(category);
                return true;
            } else {
                return false;
            }
        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public CourseCategory findOneAvailable(Long id) {
        checkId(id);
        return courseCategoryRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<CourseCategory> findAllAvailable() {
        return courseCategoryRepository.findAllByAvailable(true);
    }

    @Override
    public List<CourseCategory> findAllByQuestionnaireId(Long id) {
        return courseCategoryRepository.findAllByQuestionnaire_Id(id);
    }
}
