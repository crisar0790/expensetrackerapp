package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.exception.ExpenseNotAdded;
import com.henry.expensetracker.exception.ExpenseNotDeleted;
import com.henry.expensetracker.exception.ExpenseNotFoundException;
import com.henry.expensetracker.exception.ExpenseNotUpdated;
import com.henry.expensetracker.entity.Expense;
import com.henry.expensetracker.repository.impl.ExpenseRepositoryImpl;
import com.henry.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepositoryImpl expenseRepositoryImpl;

    public ExpenseResponse addExpense(ExpenseRequest expense) throws ExpenseNotAdded {
        return expenseRepositoryImpl.addExpense(expense);
    }

    public List<ExpenseResponse> listExpensesByUser(String email) throws ExpenseNotFoundException {
        return expenseRepositoryImpl.listExpensesByUser(email);
    }

    public ExpenseResponse getExpense(Long id) throws ExpenseNotFoundException {
        return expenseRepositoryImpl.getExpense(id);
    }

    public List<ExpenseResponse> getAllExpenses() throws ExpenseNotFoundException {
        return expenseRepositoryImpl.getAllExpenses();
    }

    public boolean updateExpense(ExpenseRequest expense, Long id) throws ExpenseNotUpdated {
        return expenseRepositoryImpl.updateExpense(expense, id);
    }

    public boolean deleteExpense(Long id) throws ExpenseNotDeleted {
        return expenseRepositoryImpl.deleteExpense(id);
    }
}
