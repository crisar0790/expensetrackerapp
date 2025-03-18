package com.henry.expensetracker.utils;

import com.henry.expensetracker.controller.model.response.CategoryResponse;
import com.henry.expensetracker.exception.GetCategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryUtils {
    private final String SQL_GET_CATEGORY_BY_NAME = "SELECT id FROM category WHERE name = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Long getCategoryId(String name) throws GetCategoryException {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_CATEGORY_BY_NAME, new Object[]{name}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new GetCategoryException("Category with name not found: " + name);
        }
    }
}
