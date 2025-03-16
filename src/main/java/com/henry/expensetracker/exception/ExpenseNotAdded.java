package com.henry.expensetracker.exception;

public class ExpenseNotAdded extends RuntimeException {
    public ExpenseNotAdded(String message) {
        super(message);
    }
}
