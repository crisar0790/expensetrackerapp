package com.henry.expensetracker.exception;

public class ExpenseNotUpdated extends RuntimeException {
    public ExpenseNotUpdated(String message) {
        super(message);
    }
}
