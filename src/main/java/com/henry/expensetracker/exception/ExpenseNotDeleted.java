package com.henry.expensetracker.exception;

public class ExpenseNotDeleted extends RuntimeException {
    public ExpenseNotDeleted(String message) {
        super(message);
    }
}
