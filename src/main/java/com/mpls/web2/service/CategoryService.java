package com.mpls.web2.service;

import com.mpls.web2.model.Category;

import java.util.List;

public interface CategoryService {

    Category findByName(String name);

    Category findOne(Long id);

    Category save(Category category);

    Category update(Category category);

    Boolean delete(Long id);

    Category findOneAvailable(Long id);

    List<Category> findAllAvailable();

}
