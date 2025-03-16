package com.henry.expensetracker.controller.model.response;

import java.time.LocalDate;

public class ExpenseResponse {
    private Long id;
    private double amount;
    private LocalDate date;
    private String category;

    public ExpenseResponse(Long id, double amount, LocalDate date, String category) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
