package com.mpls.web2.service.impl;

import com.mpls.web2.model.Category;
import com.mpls.web2.repository.CategoryRepository;
import com.mpls.web2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findByName(String name) {
        checkString(name);
        return categoryRepository.findByName(name);
    }

    @Override
    public Category findOne(Long id) {
        checkId(id);
        return categoryRepository.findOne(id);
    }

    @Override
    public Category save(Category category) {
        checkSave(category);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(checkUpdate(category.getId(),categoryRepository));
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            Category category = categoryRepository.findOne(id);
            if (category != null) {
                categoryRepository.delete(category);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public Category findOneAvailable(Long id) {
        checkId(id);
        return categoryRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Category> findAllAvailable() {
        return categoryRepository.findAllByAvailable(true);
    }
}
