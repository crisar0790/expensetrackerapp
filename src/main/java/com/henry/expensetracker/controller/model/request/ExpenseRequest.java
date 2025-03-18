package com.henry.expensetracker.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ExpenseRequest {
    private String userEmail;
    private double amount;
    private LocalDate date;
    private String category;
    private String description;
}
