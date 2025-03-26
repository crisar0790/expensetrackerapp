package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.entity.Expense;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.*;
import com.henry.expensetracker.repository.ExpenseRepository;
import com.henry.expensetracker.repository.UserRepository;
import com.henry.expensetracker.utils.impl.CategoryUtilsImpl;
import com.henry.expensetracker.utils.impl.UserUtilsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("These tests evaluate the methods of ExpenseService")
@ExtendWith(MockitoExtension.class)
class ExpenseServiceImplTest {
    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserUtilsImpl userUtilsImpl;

    @Mock
    private CategoryUtilsImpl categoryUtilsImpl;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    private ExpenseRequest expenseRequest;
    private User mockUser;
    private Category mockCategory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockUser = new User(1L, "John Doe", "johndoe@example.com");
        mockCategory = new Category(1L, "Food", "Expenses on meals, groceries, and dining out.");
        expenseRequest = new ExpenseRequest("johndoe@example.com", 100.0, LocalDate.now(), "Food", "Lunch");
    }

    @Test
    void testAddExpense_Success() throws ExpenseNotAdded, GetUserException, GetCategoryException {
        when(userUtilsImpl.getUserByEmail(expenseRequest.getUserEmail())).thenReturn(mockUser);
        when(categoryUtilsImpl.getCategoryByName(expenseRequest.getCategory())).thenReturn(mockCategory);

        Expense mockExpense = new Expense(1L, mockUser.getId(), mockCategory.getId(), 100.0, expenseRequest.getDate(), expenseRequest.getCategory(), expenseRequest.getDescription());
        when(expenseRepository.save(any(Expense.class))).thenReturn(mockExpense);

        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.of(mockUser));

        ExpenseResponse result = expenseService.addExpense(expenseRequest);

        assertNotNull(result);
        assertEquals("John Doe", result.getUserName());
        assertEquals(100.0, result.getAmount());
    }

    @Test
    void testAddExpense_Exception() throws GetUserException {
        when(userUtilsImpl.getUserByEmail(expenseRequest.getUserEmail())).thenThrow(new GetUserException("User not found"));

        assertThrows(GetUserException.class, () -> expenseService.addExpense(expenseRequest));
    }

    @Test
    void testListExpensesByUser_Success() throws ExpenseNotFoundException, GetUserException {
        when(userUtilsImpl.getUserByEmail("johndoe@example.com")).thenReturn(mockUser);
        when(expenseRepository.findByIdUser(mockUser.getId())).thenReturn(Arrays.asList(
                new Expense(1L, mockUser.getId(), mockCategory.getId(), 100.0, LocalDate.now(), "Food", "Lunch")
        ));
        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.of(mockUser));

        List<ExpenseResponse> expenses = expenseService.listExpensesByUser("johndoe@example.com");

        assertNotNull(expenses);
        assertEquals(1, expenses.size());

        verify(userUtilsImpl).getUserByEmail("johndoe@example.com");
        verify(expenseRepository).findByIdUser(mockUser.getId());
        verify(userRepository).findById(mockUser.getId());
    }

    @Test
    void testListExpensesByUser_Exception() throws ExpenseNotFoundException, GetUserException {
        when(userUtilsImpl.getUserByEmail("johndoe@example.com")).thenReturn(mockUser);
        when(expenseRepository.findByIdUser(mockUser.getId())).thenReturn(new ArrayList<>());

        assertThrows(ExpenseNotFoundException.class, () -> expenseService.listExpensesByUser("johndoe@example.com"));
    }

    @Test
    void testGetExpense_Success() throws ExpenseNotFoundException, GetUserException {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(new Expense(1L, mockUser.getId(), mockCategory.getId(), 100.0, LocalDate.now(), "Food", "Lunch")));
        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.of(mockUser));

        ExpenseResponse result = expenseService.getExpense(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getUserName());
    }

    @Test
    void testGetExpense_Exception() throws ExpenseNotFoundException {
        when(expenseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ExpenseNotFoundException.class, () -> expenseService.getExpense(1L));
    }

    @Test
    void testUpdateExpense_Success() throws ExpenseNotFoundException, GetUserException, GetCategoryException {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(new Expense(1L, mockUser.getId(), mockCategory.getId(), 100.0, LocalDate.now(), "Food", "Lunch")));
        when(userUtilsImpl.getUserByEmail(expenseRequest.getUserEmail())).thenReturn(mockUser);
        when(categoryUtilsImpl.getCategoryByName(expenseRequest.getCategory())).thenReturn(mockCategory);

        boolean result = expenseService.updateExpense(expenseRequest, 1L);

        assertTrue(result);
    }

    @Test
    void testUpdateExpense_Exception() throws ExpenseNotFoundException, GetUserException {
        when(expenseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ExpenseNotFoundException.class, () -> expenseService.updateExpense(expenseRequest, 1L));
    }

    @Test
    void testDeleteExpense_Success() throws ExpenseNotDeleted {
        doNothing().when(expenseRepository).deleteById(1L);

        boolean result = expenseService.deleteExpense(1L);

        assertTrue(result);
    }

    @Test
    void testDeleteExpense_Exception() throws ExpenseNotDeleted {
        doThrow(new ExpenseNotDeleted("Expense could not be deleted")).when(expenseRepository).deleteById(1L);

        assertThrows(ExpenseNotDeleted.class, () -> expenseService.deleteExpense(1L));
    }
}