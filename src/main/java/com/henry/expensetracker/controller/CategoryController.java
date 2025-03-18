package com.henry.expensetracker.controller;

import com.henry.expensetracker.controller.model.request.CategoryRequest;
import com.henry.expensetracker.controller.model.response.CategoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.henry.expensetracker.exception.AddCategoryException;
import com.henry.expensetracker.exception.GetCategoryException;
import com.henry.expensetracker.entity.Category;
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
     * Endpoint para agregar una nueva categoría.
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
     * Endpoint para obtener todas las categorías.
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

    /**
     * Endpoint para obtener el nombre de una categoría a partir de su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryName(@PathVariable Long id) {
        try {
            log.info("Getting category: {}", id);
            log.warn("The category with that id might not exist");
            String categoryName = categoryService.getCategoryName(id);
            return ResponseEntity.ok(categoryName);
        } catch (GetCategoryException e) {
            log.error("An error occurred while trying to get that category: {}", e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
