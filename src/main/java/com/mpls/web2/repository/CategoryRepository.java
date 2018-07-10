package com.mpls.web2.repository;

import com.mpls.web2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    Category findByAvailableAndId(Boolean available, Long id);

    List<Category> findAllByAvailable(Boolean available);

}
