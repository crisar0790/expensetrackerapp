package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.entity.Expense;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.*;
import com.henry.expensetracker.repository.ExpenseRepository;
import com.henry.expensetracker.utils.CategoryUtils;
import com.henry.expensetracker.utils.UserUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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
    private UserUtils userUtils;

    @Mock
    private CategoryUtils categoryUtils;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    private ExpenseRequest expenseRequest;
    private Expense expense;
    private User user;
    private Category category;

    @BeforeEach
    void setUp() {
        user = new User(1L, "John Doe", "test@example.com");
        category = new Category(1L, "Food", "Expenses for meals and groceries");
        expenseRequest = new ExpenseRequest("test@example.com", 100.0, LocalDate.now(), "Food", "Dinner at restaurant");
        expense = new Expense(1L, 1L, 1L, 100.0, LocalDate.now(), "Food", "Dinner at restaurant");
    }

    @Test
    void testAddExpense_Success() throws ExpenseNotAdded, GetUserException {
        when(userUtils.getUserByEmail(expenseRequest.getUserEmail())).thenReturn(user);
        when(categoryUtils.getCategoryByName(expenseRequest.getCategory())).thenReturn(category);
        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);

        ExpenseResponse response = expenseService.addExpense(expenseRequest);

        assertNotNull(response);
        assertEquals(100.0, response.getAmount());
        assertEquals("Food", response.getCategory());

        verify(expenseRepository, times(1)).save(any(Expense.class));
    }

    @Test
    void testListExpensesByUser_Success() throws ExpenseNotFoundException, GetUserException {
        when(userUtils.getUserByEmail(expenseRequest.getUserEmail())).thenReturn(user);
        when(expenseRepository.findByIdUser(user.getId())).thenReturn(List.of(expense));

        List<ExpenseResponse> responses = expenseService.listExpensesByUser(expenseRequest.getUserEmail());

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Food", responses.get(0).getCategory());

        verify(expenseRepository, times(1)).findByIdUser(user.getId());
    }

    @Test
    void testGetExpense_Success() throws ExpenseNotFoundException {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));

        ExpenseResponse response = expenseService.getExpense(1L);

        assertNotNull(response);
        assertEquals(100.0, response.getAmount());
        assertEquals("Food", response.getCategory());

        verify(expenseRepository, times(1)).findById(1L);
    }

    @Test
    void testGetExpense_NotFound() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ExpenseNotFoundException.class, () -> expenseService.getExpense(1L));

        verify(expenseRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllExpenses_Success() throws ExpenseNotFoundException {
        List<Expense> expenses = Arrays.asList(expense);
        when(expenseRepository.findAll()).thenReturn(expenses);

        List<ExpenseResponse> responses = expenseService.getAllExpenses();

        assertNotNull(responses);
        assertEquals(1, responses.size());

        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    void testUpdateExpense_Success() throws ExpenseNotUpdated, ExpenseNotFoundException, GetUserException {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));
        when(userUtils.getUserByEmail(expenseRequest.getUserEmail())).thenReturn(user);
        when(categoryUtils.getCategoryByName(expenseRequest.getCategory())).thenReturn(category);

        boolean updated = expenseService.updateExpense(expenseRequest, 1L);

        assertTrue(updated);
        verify(expenseRepository, times(1)).save(any(Expense.class));
    }

    @Test
    void testDeleteExpense_Success() throws ExpenseNotDeleted {
        doNothing().when(expenseRepository).deleteById(1L);

        boolean deleted = expenseService.deleteExpense(1L);

        assertTrue(deleted);
        verify(expenseRepository, times(1)).deleteById(1L);
    }
}