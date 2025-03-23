package com.henry.expensetracker.service;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.entity.Expense;
import com.henry.expensetracker.exception.*;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    ExpenseResponse addExpense(ExpenseRequest expense) throws ExpenseNotAdded, GetUserException;

    List<ExpenseResponse> listExpensesByUser(String email) throws ExpenseNotFoundException, GetUserException;

    ExpenseResponse getExpense(Long id) throws ExpenseNotFoundException;

    List<ExpenseResponse> getAllExpenses() throws ExpenseNotFoundException;

    boolean updateExpense(ExpenseRequest expense, Long id) throws ExpenseNotUpdated, ExpenseNotFoundException, GetUserException;

    boolean deleteExpense(Long id) throws ExpenseNotDeleted;
}
