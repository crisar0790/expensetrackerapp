package com.henry.expensetracker.controller;

import com.henry.expensetracker.controller.model.request.CategoryRequest;
import com.henry.expensetracker.controller.model.response.CategoryResponse;
import org.springframework.web.bind.annotation.*;
import com.henry.expensetracker.exception.AddCategoryException;
import com.henry.expensetracker.exception.GetCategoryException;
import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * Endpoint para agregar una nueva categoría.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest category) {
        try {
            CategoryResponse createdCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(createdCategory);
        } catch (AddCategoryException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Endpoint para obtener todas las categorías.
     */
    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            List<CategoryResponse> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (GetCategoryException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Endpoint para obtener el nombre de una categoría a partir de su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryName(@PathVariable Long id) {
        try {
            String categoryName = categoryService.getCategoryName(id);
            return ResponseEntity.ok(categoryName);
        } catch (GetCategoryException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
