package com.henry.expensetracker.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExpenseCategoryByUserResponse {
    private String category;
    private double amount;
}
