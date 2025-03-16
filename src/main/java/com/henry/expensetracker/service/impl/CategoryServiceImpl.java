package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.CategoryRequest;
import com.henry.expensetracker.controller.model.response.CategoryResponse;
import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.exception.AddCategoryException;
import com.henry.expensetracker.exception.GetCategoryException;
import com.henry.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.henry.expensetracker.repository.impl.CategoryRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepositoryImpl categoryRepositoryImpl;

    public CategoryResponse addCategory(CategoryRequest category) throws AddCategoryException {
        return categoryRepositoryImpl.addCategory(category);
    }

    public List<CategoryResponse> getAllCategories() throws GetCategoryException {
        return categoryRepositoryImpl.getAllCategories().stream()
                .map(this::mapCategoryResponse)
                .collect(Collectors.toList());
    }

    public String getCategoryName(Long id) throws GetCategoryException {
        return categoryRepositoryImpl.getCategoryName(id.intValue());
    }

    private CategoryResponse mapCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getName()
        );
    }
}
