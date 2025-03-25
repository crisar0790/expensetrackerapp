package com.henry.expensetracker.controller;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.exception.*;
import com.henry.expensetracker.entity.Expense;
import com.henry.expensetracker.service.impl.ExpenseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseServiceImpl expenseService;

    /**
     * Endpoint to add a new expense.
     * date format (yyyy-MM-dd).
     */
    @PostMapping
    public ResponseEntity<?> addExpense(
            @RequestBody ExpenseRequest expense) {
        try {
            log.info("Adding a new expense: {}", expense.getDescription());
            ExpenseResponse expenseResponse = expenseService.addExpense(expense);
            return ResponseEntity.ok(expenseResponse);
        } catch (ExpenseNotAdded e) {
            log.error("An error occurred while trying to save the new expense: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (GetUserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Endpoint to get user expenses.
     */
    @GetMapping("/user/{email}")
    public ResponseEntity<?> listExpensesByUser(@PathVariable String email) {
        try {
            log.info("Getting all expenses of: {}", email);
            log.warn("Repository is processing a list of expenses");
            List<ExpenseResponse> expenses = expenseService.listExpensesByUser(email);
            return ResponseEntity.ok(expenses);
        } catch (ExpenseNotFoundException e) {
            log.error("An error occurred while trying to get all expenses: {}", e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (GetUserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Endpoint to get expense by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getExpense(@PathVariable Long id) {
        try {
            log.info("Getting expense: {}", id);
            log.warn("The expense with that id might not exist");
            ExpenseResponse expense = expenseService.getExpense(id);
            return ResponseEntity.ok(expense);
        } catch (ExpenseNotFoundException e) {
            log.error("An error occurred while trying to get that expense: {}", e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (GetUserException e) {
            throw new RuntimeException("Error obteniendo el usuario", e);
        }
    }

    /**
     * Endpoint to get all expenses.
     */
    @GetMapping
    public ResponseEntity<?> getAllExpenses() {
        try {
            log.info("Getting all expenses");
            log.warn("Repository is processing a list of expenses");
            List<ExpenseResponse> expenses = expenseService.getAllExpenses();
            return ResponseEntity.ok(expenses);
        } catch (ExpenseNotFoundException e) {
            log.error("An error occurred while trying to get all categories: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Endpoint to update expense.
     * date format (yyyy-MM-dd).
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(
            @PathVariable Long id,
            @RequestBody ExpenseRequest expense) {
        try {
            log.info("Updating expense: {}", id);
            log.warn("The expense with that id might not exist");
            boolean updated = expenseService.updateExpense(expense, id);
            if (updated) {
                return ResponseEntity.ok("Expense updated.");
            } else {
                return ResponseEntity.status(404).body("Expense not found");
            }
        } catch (ExpenseNotUpdated e) {
            log.error("An error occurred while trying to update the expense: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while trying to update the expense: {}", e.getMessage());
            return ResponseEntity.status(400).body("Invalid date format. Please use ISO format (yyyy-MM-dd)");
        }
    }

    /**
     * Endpoint to delete expense.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        try {
            log.info("Deleting expense: {}", id);
            log.warn("The expense with that id might not exist");
            boolean deleted = expenseService.deleteExpense(id);
            if (deleted) {
                return ResponseEntity.ok("Expense deleted");
            } else {
                return ResponseEntity.status(404).body("Expense not found");
            }
        } catch (ExpenseNotDeleted e) {
            log.error("An error occurred while trying to delete the expense: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
