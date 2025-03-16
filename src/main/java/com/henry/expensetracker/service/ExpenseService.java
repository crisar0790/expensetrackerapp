package com.henry.expensetracker.service;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.entity.Expense;
import com.henry.expensetracker.exception.ExpenseNotAdded;
import com.henry.expensetracker.exception.ExpenseNotDeleted;
import com.henry.expensetracker.exception.ExpenseNotFoundException;
import com.henry.expensetracker.exception.ExpenseNotUpdated;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    ExpenseResponse addExpense(ExpenseRequest expense) throws ExpenseNotAdded;

    List<ExpenseResponse> listExpensesByUser(String email) throws ExpenseNotFoundException;

    ExpenseResponse getExpense(Long id) throws ExpenseNotFoundException;

    List<ExpenseResponse> getAllExpenses() throws ExpenseNotFoundException;

    boolean updateExpense(ExpenseRequest expense, Long id) throws ExpenseNotUpdated;

    boolean deleteExpense(Long id) throws ExpenseNotDeleted;
}
