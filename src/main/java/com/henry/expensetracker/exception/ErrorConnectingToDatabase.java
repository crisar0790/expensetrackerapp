package com.henry.expensetracker.exception;

public class ErrorConnectingToDatabase extends Exception {
    public ErrorConnectingToDatabase(String message) {
        super(message);
    }
}
