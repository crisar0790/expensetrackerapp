package com.henry.expensetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Expense {
    private Long id;
    private Long idUser;
    private Long idCategory;
    private double amount;
    private LocalDate date;
    private String category;
    private String description;
}
