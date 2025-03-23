package com.henry.expensetracker.controller;

import com.henry.expensetracker.controller.model.request.CategoryRequest;
import com.henry.expensetracker.controller.model.response.CategoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.henry.expensetracker.exception.AddCategoryException;
import com.henry.expensetracker.exception.GetCategoryException;
import com.henry.expensetracker.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * Endpoint to add a new category.
     */
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest category) {
        try {
            log.info("Adding a new category: {}", category.getName());
            CategoryResponse createdCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(createdCategory);
        } catch (AddCategoryException e) {
            log.error("An error occurred while trying to save the new category: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Endpoint get all categories.
     */
    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            log.info("Getting all categories");
            log.warn("Repository is processing a list of categories");
            List<CategoryResponse> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (GetCategoryException e) {
            log.error("An error occurred while trying to get all categories: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
