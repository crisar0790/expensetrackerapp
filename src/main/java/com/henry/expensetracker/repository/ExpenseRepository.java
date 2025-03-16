package com.henry.expensetracker.repository;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.exception.ExpenseNotAdded;
import com.henry.expensetracker.exception.ExpenseNotDeleted;
import com.henry.expensetracker.exception.ExpenseNotFoundException;
import com.henry.expensetracker.exception.ExpenseNotUpdated;
import com.henry.expensetracker.entity.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository {
    ExpenseResponse addExpense(ExpenseRequest expense) throws ExpenseNotAdded;

    List<ExpenseResponse> listExpensesByUser(String email) throws ExpenseNotFoundException;

    ExpenseResponse getExpense(Long id) throws ExpenseNotFoundException;

    List<ExpenseResponse> getAllExpenses() throws ExpenseNotFoundException;

    boolean updateExpense(ExpenseRequest expense, Long id) throws ExpenseNotUpdated;

    boolean deleteExpense(Long id) throws ExpenseNotDeleted;
}
