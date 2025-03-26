package com.henry.expensetracker.utils;

import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.exception.GetCategoryException;

public interface CategoryUtils {
    Category getCategoryByName(String name) throws GetCategoryException;
}
