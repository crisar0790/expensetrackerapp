package com.henry.expensetracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "id_category")
    private Long idCategory;
    private double amount;
    private LocalDate date;
    private String category;
    private String description;

    public Expense(Long idUser, Long idCategory, double amount, LocalDate date, String category, String description) {
        this.idUser = idUser;
        this.idCategory = idCategory;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
    }
}
