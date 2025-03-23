package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.CategoryRequest;
import com.henry.expensetracker.controller.model.response.CategoryResponse;
import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.exception.AddCategoryException;
import com.henry.expensetracker.exception.GetCategoryException;
import com.henry.expensetracker.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("These tests evaluate the methods of CategoryService")
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private CategoryRequest categoryRequest;
    private Category category;

    @BeforeEach
    void setUp() {
        categoryRequest = new CategoryRequest("Food", "Expenses for meals and groceries");
        category = new Category(1L, "Food", "Expenses for meals and groceries");
    }

    @Test
    void testAddCategory_Success() throws AddCategoryException {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryResponse response = categoryService.addCategory(categoryRequest);

        assertNotNull(response);
        assertEquals("Food", response.getName());

        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testAddCategory_Exception() {
        doThrow(new RuntimeException("Error al guardar categoría")).when(categoryRepository).save(any(Category.class));

        assertThrows(RuntimeException.class, () -> categoryService.addCategory(categoryRequest));

        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testGetAllCategories_Success() throws GetCategoryException {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Food", "Expenses for meals and groceries"),
                new Category(2L, "Transport", "Expenses for commuting and travel")
        );
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryResponse> responses = categoryService.getAllCategories();

        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals("Food", responses.get(0).getName());
        assertEquals("Transport", responses.get(1).getName());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetAllCategories_EmptyList() throws GetCategoryException {
        when(categoryRepository.findAll()).thenReturn(List.of());

        List<CategoryResponse> responses = categoryService.getAllCategories();

        assertNotNull(responses);
        assertTrue(responses.isEmpty());

        verify(categoryRepository, times(1)).findAll();
    }
}