package com.henry.expensetracker.utils.impl;

import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.exception.GetCategoryException;
import com.henry.expensetracker.repository.CategoryRepository;
import com.henry.expensetracker.utils.CategoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class CategoryUtilsImpl implements CategoryUtils {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryByName(String name) throws GetCategoryException {
        try {
            return categoryRepository.findByName(name);
        } catch (EmptyResultDataAccessException e) {
            throw new GetCategoryException("Category with name not found: " + name);
        }
    }
}
