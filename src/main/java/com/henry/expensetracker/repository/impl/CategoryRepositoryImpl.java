package com.henry.expensetracker.repository.impl;

import com.henry.expensetracker.controller.model.request.CategoryRequest;
import com.henry.expensetracker.controller.model.response.CategoryResponse;
import com.henry.expensetracker.exception.AddCategoryException;
import com.henry.expensetracker.exception.GetCategoryException;
import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final String SQL_ADD_CATEGORY = "INSERT INTO category (name, description) VALUES (?, ?)";
    private final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM category";
    private final String SQL_GET_CATEGORY_NAME = "SELECT name FROM category WHERE id = ?";
    private final String SQL_GET_CATEGORY_BY_NAME = "SELECT name FROM category WHERE name = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CategoryResponse addCategory(CategoryRequest category) throws AddCategoryException {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(SQL_ADD_CATEGORY,
                    category.getName(),
                    category.getDescription()
                    );

            return new CategoryResponse(category.getName());
        } catch (DataAccessException e) {
            throw new AddCategoryException("Error adding category: " + e.getMessage());
        }
    }

    @Override
    public List<Category> getAllCategories() throws GetCategoryException {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_CATEGORIES, categoryRowMapper);
        } catch (DataAccessException e) {
            throw new GetCategoryException("Error getting all categories: " + e.getMessage());
        }
    }

    @Override
    public String getCategoryName(int id) throws GetCategoryException {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_CATEGORY_NAME, new Object[]{id}, String.class);
        } catch (EmptyResultDataAccessException e) {
            throw new GetCategoryException("Category with ID not found: " + id);
        } catch (DataAccessException e) {
            throw new GetCategoryException("Error getting category name: " + e.getMessage());
        }
    }

    public Long getCategoryId(String name) throws GetCategoryException {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_CATEGORY_BY_NAME, new Object[]{name}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new GetCategoryException("Category with name not found: " + name);
        }
    }

    private final RowMapper<Category> categoryRowMapper = (rs, rowNum) ->
            new Category(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("description")
            );
}
