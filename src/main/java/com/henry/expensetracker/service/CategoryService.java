package com.henry.expensetracker.service;

import com.henry.expensetracker.controller.model.request.CategoryRequest;
import com.henry.expensetracker.controller.model.response.CategoryResponse;
import com.henry.expensetracker.exception.AddCategoryException;
import com.henry.expensetracker.exception.GetCategoryException;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest category) throws AddCategoryException;
    List<CategoryResponse> getAllCategories() throws GetCategoryException;
}
