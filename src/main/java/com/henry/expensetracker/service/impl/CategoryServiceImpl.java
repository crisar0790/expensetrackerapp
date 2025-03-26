package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.CategoryRequest;
import com.henry.expensetracker.controller.model.response.CategoryResponse;
import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.exception.AddCategoryException;
import com.henry.expensetracker.exception.GetCategoryException;
import com.henry.expensetracker.repository.CategoryRepository;
import com.henry.expensetracker.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) throws AddCategoryException {
        Category category = mapToCategory(categoryRequest);
        categoryRepository.save(category);
        log.info("New category added successfully");

        return mapToCategoryResponse(category);
    }

    @Override    public List<CategoryResponse> getAllCategories() throws GetCategoryException {
        return categoryRepository.findAll().stream()
                .map(this::mapToCategoryResponse)
                .collect(Collectors.toList());
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getName()
        );
    }

    private Category mapToCategory(CategoryRequest categoryRequest) {
        return new Category(
                categoryRequest.getName(),
                categoryRequest.getDescription()
        );
    }
}
