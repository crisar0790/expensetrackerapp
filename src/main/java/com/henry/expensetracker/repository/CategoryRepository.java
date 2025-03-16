package com.henry.expensetracker.repository;

import com.henry.expensetracker.controller.model.request.CategoryRequest;
import com.henry.expensetracker.controller.model.response.CategoryResponse;
import com.henry.expensetracker.exception.AddCategoryException;
import com.henry.expensetracker.exception.GetCategoryException;
import com.henry.expensetracker.entity.Category;

import java.util.List;

public interface CategoryRepository {
    CategoryResponse addCategory(CategoryRequest category) throws AddCategoryException;

    List<Category> getAllCategories() throws GetCategoryException;

    String getCategoryName(int id) throws GetCategoryException;

    Long getCategoryId(String name) throws GetCategoryException;
}
