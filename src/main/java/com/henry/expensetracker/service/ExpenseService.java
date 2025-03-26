package com.henry.expensetracker.service;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseCategoryByUserResponse;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.exception.*;

import java.util.List;

public interface ExpenseService {
    ExpenseResponse addExpense(ExpenseRequest expense) throws ExpenseNotAdded, GetUserException;

    List<ExpenseResponse> listExpensesByUser(String email) throws ExpenseNotFoundException, GetUserException;

    ExpenseResponse getExpense(Long id) throws ExpenseNotFoundException, GetUserException;

    List<ExpenseResponse> getAllExpenses() throws ExpenseNotFoundException;

    boolean updateExpense(ExpenseRequest expense, Long id) throws ExpenseNotUpdated, ExpenseNotFoundException, GetUserException;

    boolean deleteExpense(Long id) throws ExpenseNotDeleted;

    Double getTotalExpenseByUser(String email) throws ExpenseNotFoundException, GetUserException;

    List<ExpenseCategoryByUserResponse> getTotalExpensesByUserGroupedByCategory(String email) throws ExpenseNotFoundException, GetUserException;
}
