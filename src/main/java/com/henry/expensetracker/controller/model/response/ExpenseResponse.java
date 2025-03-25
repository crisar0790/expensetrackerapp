package com.henry.expensetracker.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ExpenseResponse {
    private Long id;
    private String userName;
    private double amount;
    private LocalDate date;
    private String category;
}
