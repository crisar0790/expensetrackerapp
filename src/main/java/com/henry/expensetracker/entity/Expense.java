package com.henry.expensetracker.entity;

import java.time.LocalDate;

public class Expense {
    private Long id;
    private Long idUser;
    private Long idCategory;
    private double amount;
    private LocalDate date;
    private String category;
    private String description;

    public Expense(Long id, double amount, LocalDate date, String category, String description) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
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
        this.category = category.toLowerCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "entity.Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
