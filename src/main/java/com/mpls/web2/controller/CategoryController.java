package com.mpls.web2.controller;

import com.mpls.web2.model.Category;
import com.mpls.web2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/find-by-name")
    private ResponseEntity<Category> findByName(@RequestParam String name) {
        Category category = categoryService.findByName(name);
        if (category == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<Category> findOne(@PathVariable Long id) {
        Category category = categoryService.findOne(id);
        if (category == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(category);
    }


    @PostMapping("/save")
    private ResponseEntity<Category> save(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    @PutMapping("/update")
    private ResponseEntity<Category> update(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(category));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(categoryService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT).build();
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<Category> findOneAvailable(@PathVariable Long id) {
        Category category = categoryService.findOneAvailable(id);
        if (category == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<Category>> findAllAvailable() {
        List<Category> categories = categoryService.findAllAvailable();
        return ResponseEntity.ok(categories);

    }

}
