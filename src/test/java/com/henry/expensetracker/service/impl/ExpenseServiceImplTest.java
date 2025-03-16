package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.entity.Expense;
import com.henry.expensetracker.exception.ExpenseNotAdded;
import com.henry.expensetracker.exception.ExpenseNotDeleted;
import com.henry.expensetracker.exception.ExpenseNotFoundException;
import com.henry.expensetracker.exception.ExpenseNotUpdated;
import com.henry.expensetracker.repository.impl.ExpenseRepositoryImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("These tests evaluate the methods of ExpenseService")
@ExtendWith(MockitoExtension.class)
class ExpenseServiceImplTest {
    @Mock
    private ExpenseRepositoryImpl expenseRepositoryImpl;

    @InjectMocks
    private ExpenseServiceImpl expenseServiceImpl;

    private ExpenseRequest expenseRequest;
    private Expense expense;
    private ExpenseResponse expenseResponse;

    @BeforeEach
    void setUp() {
        expenseRequest = new ExpenseRequest("user@example.com", 100.0, LocalDate.now(), "Food", "Lunch");
        expense = new Expense(1L, 100.0, LocalDate.now(), "Food", "Lunch");
        expenseResponse = new ExpenseResponse(1L, 100.0, LocalDate.now(), "Food");
    }

    @DisplayName("addExpense should return ExpenseResonse qhen Expense is added successfully")
    @Test
    void addExpense_ShouldReturnExpenseResponse_WhenExpenseIsAddedSuccessfully() throws ExpenseNotAdded {
        when(expenseRepositoryImpl.addExpense(expenseRequest)).thenReturn(expenseResponse);

        ExpenseResponse response = expenseServiceImpl.addExpense(expenseRequest);

        assertNotNull(response);
        assertEquals(100.0, response.getAmount());
        assertEquals("Food", response.getCategory());

        verify(expenseRepositoryImpl, times(1)).addExpense(expenseRequest);
    }

    @DisplayName("listExpenseByUser should return a List of Expenses when user has expenses")
    @Test
    void listExpensesByUser_ShouldReturnListOfExpenses_WhenUserHasExpenses() throws ExpenseNotFoundException {
        List<ExpenseResponse> expenses = Arrays.asList(
                new ExpenseResponse(1L, 50.0, LocalDate.now(), "Transport"),
                new ExpenseResponse(2L, 30.0, LocalDate.now(), "Entertainment")
        );

        when(expenseRepositoryImpl.listExpensesByUser("user@example.com")).thenReturn(expenses);

        List<ExpenseResponse> responses = expenseServiceImpl.listExpensesByUser("user@example.com");

        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals("Transport", responses.get(0).getCategory());
        assertEquals("Entertainment", responses.get(1).getCategory());

        verify(expenseRepositoryImpl, times(1)).listExpensesByUser("user@example.com");
    }

    @DisplayName("getExpense should return ExpenseResponse when id is valid")
    @Test
    void getExpense_ShouldReturnExpenseResponse_WhenIdIsValid() throws ExpenseNotFoundException {
        when(expenseRepositoryImpl.getExpense(1L)).thenReturn(expenseResponse);

        ExpenseResponse response = expenseServiceImpl.getExpense(1L);

        assertNotNull(response);
        assertEquals(100.0, response.getAmount());
        assertEquals("Food", response.getCategory());

        verify(expenseRepositoryImpl, times(1)).getExpense(1L);
    }

    @DisplayName("getAllExpenses should return a List of all Expenses")
    @Test
    void getAllExpenses_ShouldReturnListOfAllExpenses() throws ExpenseNotFoundException {
        List<ExpenseResponse> expenses = Arrays.asList(
                new ExpenseResponse(1L, 100.0, LocalDate.now(), "Food"),
                new ExpenseResponse(2L, 50.0, LocalDate.now(), "Transport")
        );

        when(expenseRepositoryImpl.getAllExpenses()).thenReturn(expenses);

        List<ExpenseResponse> responses = expenseServiceImpl.getAllExpenses();

        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals("Food", responses.get(0).getCategory());
        assertEquals("Transport", responses.get(1).getCategory());

        verify(expenseRepositoryImpl, times(1)).getAllExpenses();
    }

    @DisplayName("updateExpense ahould return true when Expense is updated successfully")
    @Test
    void updateExpense_ShouldReturnTrue_WhenExpenseIsUpdatedSuccessfully() throws ExpenseNotUpdated {
        when(expenseRepositoryImpl.updateExpense(expenseRequest, 1L)).thenReturn(true);

        boolean updated = expenseServiceImpl.updateExpense(expenseRequest, 1L);

        assertTrue(updated);
        verify(expenseRepositoryImpl, times(1)).updateExpense(expenseRequest, 1L);
    }

    @DisplayName("deleteExpense should return true when Expense is deleted successfully")
    @Test
    void deleteExpense_ShouldReturnTrue_WhenExpenseIsDeletedSuccessfully() throws ExpenseNotDeleted {
        when(expenseRepositoryImpl.deleteExpense(1L)).thenReturn(true);

        boolean deleted = expenseServiceImpl.deleteExpense(1L);

        assertTrue(deleted);
        verify(expenseRepositoryImpl, times(1)).deleteExpense(1L);
    }

    @DisplayName("addExpense should throw Exception when this method fails")
    @Test
    void addExpense_ShouldThrowException_WhenRepositoryFails() throws ExpenseNotAdded {
        when(expenseRepositoryImpl.addExpense(expenseRequest)).thenThrow(new ExpenseNotAdded("Error adding expense"));

        ExpenseNotAdded exception = assertThrows(ExpenseNotAdded.class, () -> {
            expenseServiceImpl.addExpense(expenseRequest);
        });

        assertEquals("Error adding expense", exception.getMessage());

        verify(expenseRepositoryImpl, times(1)).addExpense(expenseRequest);
    }

    @DisplayName("getExpense should throw Exception when can not found Expense")
    @Test
    void getExpense_ShouldThrowException_WhenExpenseNotFound() throws ExpenseNotFoundException {
        when(expenseRepositoryImpl.getExpense(1L)).thenThrow(new ExpenseNotFoundException("Expense not found"));

        ExpenseNotFoundException exception = assertThrows(ExpenseNotFoundException.class, () -> {
            expenseServiceImpl.getExpense(1L);
        });

        assertEquals("Expense not found", exception.getMessage());

        verify(expenseRepositoryImpl, times(1)).getExpense(1L);
    }

    @DisplayName("updateExpense should throw Exception when update fails")
    @Test
    void updateExpense_ShouldThrowException_WhenUpdateFails() throws ExpenseNotUpdated {
        when(expenseRepositoryImpl.updateExpense(expenseRequest, 1L)).thenThrow(new ExpenseNotUpdated("Update failed"));

        ExpenseNotUpdated exception = assertThrows(ExpenseNotUpdated.class, () -> {
            expenseServiceImpl.updateExpense(expenseRequest, 1L);
        });

        assertEquals("Update failed", exception.getMessage());

        verify(expenseRepositoryImpl, times(1)).updateExpense(expenseRequest, 1L);
    }

    @DisplayName("deleteExpense should throw Exception when delete fails")
    @Test
    void deleteExpense_ShouldThrowException_WhenDeleteFails() throws ExpenseNotDeleted {
        when(expenseRepositoryImpl.deleteExpense(1L)).thenThrow(new ExpenseNotDeleted("Delete failed"));

        ExpenseNotDeleted exception = assertThrows(ExpenseNotDeleted.class, () -> {
            expenseServiceImpl.deleteExpense(1L);
        });

        assertEquals("Delete failed", exception.getMessage());

        verify(expenseRepositoryImpl, times(1)).deleteExpense(1L);
    }
}