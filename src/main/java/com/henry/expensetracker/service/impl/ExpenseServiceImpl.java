package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.entity.Category;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.*;
import com.henry.expensetracker.entity.Expense;
import com.henry.expensetracker.repository.ExpenseRepository;
import com.henry.expensetracker.repository.UserRepository;
import com.henry.expensetracker.service.ExpenseService;
import com.henry.expensetracker.utils.impl.CategoryUtilsImpl;
import com.henry.expensetracker.utils.impl.UserUtilsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserUtilsImpl userUtilsImpl;
    @Autowired
    private CategoryUtilsImpl categoryUtilsImpl;
    @Autowired
    private UserRepository userRepository;

    public ExpenseResponse addExpense(ExpenseRequest expenseRequest) throws ExpenseNotAdded, GetUserException {
        Expense expense = mapToExpense(expenseRequest);
        expenseRepository.save(expense);
        log.info("New expense added successfully");

        return mapToExpenseResponse(expense);
    }

    public List<ExpenseResponse> listExpensesByUser(String email) throws ExpenseNotFoundException, GetUserException {
        User user = userUtilsImpl.getUserByEmail(email);
        List<ExpenseResponse> expenseResponses = new ArrayList<>();

        List<Expense> expenses = expenseRepository.findByIdUser(user.getId());
        log.info("All expenses were obtained");

        if (expenses == null || expenses.isEmpty()) {
            throw new ExpenseNotFoundException("No expenses found for user with email: " + email);
        }

        for (Expense expense : expenses) {
            expenseResponses.add(mapToExpenseResponse(expense));
        }

        return expenseResponses;
    }

    public ExpenseResponse getExpense(Long id) throws ExpenseNotFoundException, GetUserException {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense with ID: " + id + " did not found"));
        log.info("Expenses {} was obtained", id);

        return mapToExpenseResponse(expense);
    }

    public List<ExpenseResponse> getAllExpenses() throws ExpenseNotFoundException {
        try {
            List<ExpenseResponse> expenseResponses = new ArrayList<>();
            List<Expense> expenses = expenseRepository.findAll();
            log.info("All expenses were obtained");

            if (expenses.isEmpty()) {
                throw new ExpenseNotFoundException("No expenses found");
            }

            for (Expense expense : expenses) {
                expenseResponses.add(mapToExpenseResponse(expense));
            }

            return expenseResponses;
        } catch (GetUserException e) {
            log.error("Error getting expenses by user");
            throw new RuntimeException("Error getting user");
        }
    }

    public boolean updateExpense(ExpenseRequest expenseRequest, Long id) throws ExpenseNotUpdated, ExpenseNotFoundException, GetUserException {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense with ID: " + id + " did not found"));

        Expense expenseToUpdate = mapToExpense(expenseRequest);
        expense.setIdUser(expenseToUpdate.getIdUser());
        expense.setIdCategory(expenseToUpdate.getIdCategory());
        expense.setAmount(expenseToUpdate.getAmount());
        expense.setDate(expenseToUpdate.getDate());
        expense.setCategory(expenseToUpdate.getCategory());
        expense.setDescription(expenseToUpdate.getDescription());

        expenseRepository.save(expense);
        log.info("Expense updated successfully");

        return true;
    }

    public boolean deleteExpense(Long id) throws ExpenseNotDeleted {
        expenseRepository.deleteById(id);
        log.info("Expense deleted successfully");

        return true;
    }

    private Expense mapToExpense(ExpenseRequest expenseRequest) throws GetUserException, GetCategoryException {
        User user = userUtilsImpl.getUserByEmail(expenseRequest.getUserEmail());
        Category category = categoryUtilsImpl.getCategoryByName(expenseRequest.getCategory());
        return new Expense(
                user.getId(),
                category.getId(),
                expenseRequest.getAmount(),
                expenseRequest.getDate(),
                expenseRequest.getCategory(),
                expenseRequest.getDescription()
        );
    }

    private ExpenseResponse mapToExpenseResponse(Expense expense) throws GetUserException {
        User user = userRepository.findById(expense.getIdUser())
                .orElseThrow(() -> new GetUserException("User with ID: " + expense.getIdUser() + " did not found"));
        return new ExpenseResponse(
                expense.getId(),
                user.getName(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCategory()
        );
    }
}
