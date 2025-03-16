package com.henry.expensetracker.controller.model.request;

import java.time.LocalDate;

public class ExpenseRequest {
    private String userEmail;
    private double amount;
    private LocalDate date;
    private String category;
    private String description;

    public ExpenseRequest(String userEmail, double amount, LocalDate date, String category, String description) {
        this.userEmail = userEmail;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
