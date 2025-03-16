package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.CategoryRequest;
import com.henry.expensetracker.controller.model.response.CategoryResponse;
import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.exception.AddCategoryException;
import com.henry.expensetracker.exception.GetCategoryException;
import com.henry.expensetracker.repository.impl.CategoryRepositoryImpl;
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
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    private CategoryRequest categoryRequest;
    private Category category;
    private CategoryResponse categoryResponse;

    @BeforeEach
    void setUp() {
        categoryRequest = new CategoryRequest("Electronics", "Devices and gadgets");
        category = new Category(1L, "Electronics", "Devices and gadgets");
        categoryResponse = new CategoryResponse("Electronics");
    }

    @DisplayName("addCategory should return CategoryResponse when Category is added successfully")
    @Test
    void addCategory_ShouldReturnCategoryResponse_WhenCategoryIsAddedSuccessfully() throws AddCategoryException {
        when(categoryRepositoryImpl.addCategory(categoryRequest)).thenReturn(categoryResponse);

        CategoryResponse response = categoryServiceImpl.addCategory(categoryRequest);

        assertNotNull(response);
        assertEquals("Electronics", response.getName());

        verify(categoryRepositoryImpl, times(1)).addCategory(categoryRequest);
    }

    @DisplayName("getAllCategories should return CategoryResponses List when categories exist")
    @Test
    void getAllCategories_ShouldReturnCategoryResponsesList_WhenCategoriesExist() throws GetCategoryException {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Electronics", "Devices and gadgets"),
                new Category(2L, "Books", "Educational and entertainment books")
        );

        when(categoryRepositoryImpl.getAllCategories()).thenReturn(categories);

        List<CategoryResponse> responses = categoryServiceImpl.getAllCategories();

        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals("Electronics", responses.get(0).getName());
        assertEquals("Books", responses.get(1).getName());
        verify(categoryRepositoryImpl, times(1)).getAllCategories();
    }

    @DisplayName("getCategoryName should return Category name when Id is valid")
    @Test
    void getCategoryName_ShouldReturnCategoryName_WhenIdIsValid() throws GetCategoryException {
        when(categoryRepositoryImpl.getCategoryName(1)).thenReturn("Electronics");

        String categoryName = categoryServiceImpl.getCategoryName(1L);

        assertNotNull(categoryName);
        assertEquals("Electronics", categoryName);

        verify(categoryRepositoryImpl, times(1)).getCategoryName(1);
    }

    @DisplayName("addCategory should throw Exception when this method fails")
    @Test
    void addCategory_ShouldThrowException_WhenRepositoryFails() throws AddCategoryException {
        when(categoryRepositoryImpl.addCategory(categoryRequest)).thenThrow(new AddCategoryException("Error adding category"));

        AddCategoryException exception = assertThrows(AddCategoryException.class, () -> {
            categoryServiceImpl.addCategory(categoryRequest);
        });

        assertEquals("Error adding category", exception.getMessage());

        verify(categoryRepositoryImpl, times(1)).addCategory(categoryRequest);
    }

    @DisplayName("getCategoryName should throw Exception when this method fails")
    @Test
    void getCategoryName_ShouldThrowException_WhenRepositoryFails() throws GetCategoryException {
        when(categoryRepositoryImpl.getCategoryName(1)).thenThrow(new GetCategoryException("Category not found"));

        GetCategoryException exception = assertThrows(GetCategoryException.class, () -> {
            categoryServiceImpl.getCategoryName(1L);
        });

        assertEquals("Category not found", exception.getMessage());

        verify(categoryRepositoryImpl, times(1)).getCategoryName(1);
    }
}