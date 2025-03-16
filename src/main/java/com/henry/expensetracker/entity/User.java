package com.henry.expensetracker.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private String email;
    private List<Expense> expenses;

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.expenses = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

//    public void addExpense(Expense expense) {
//        ExpenseValidator validator = exp -> exp.getAmount() > 0 && exp.getDate() != null && !exp.getCategory().isEmpty();
//        if (validator.validate(expense)) {
//            expenses.add(expense);
//            System.out.println("entity.Expense added: " + expense);
//        } else {
//            System.out.println("Invalid expense. Not added.");
//        }
//    }
//
//    public void removeExpense(Long id) throws ExpenseNotFoundException {
//        Expense expenseToRemove = findExpenseById(id);
//        expenses.remove(expenseToRemove);
//        System.out.println("entity.Expense removed: " + expenseToRemove);
//    }
//
//    public void updateExpense(Long id, Expense newExpense) throws ExpenseNotFoundException {
//        Expense expenseToUpdate = findExpenseById(id);
//        expenseToUpdate.setAmount(newExpense.getAmount());
//        expenseToUpdate.setDate(newExpense.getDate());
//        expenseToUpdate.setCategory(newExpense.getCategory());
//        expenseToUpdate.setDescription(newExpense.getDescription());
//        System.out.println("entity.Expense updated: " + expenseToUpdate);
//    }
//
//    public void listExpenses() {
//        if (expenses.isEmpty()) {
//            System.out.println("No expenses found.");
//        } else {
//            expenses.forEach(System.out::println);
//        }
//    }
//
//    private Expense findExpenseById(Long id) throws ExpenseNotFoundException {
//        return expenses.stream()
//                .filter(expense -> expense.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new ExpenseNotFoundException("entity.Expense with ID " + id + " not found."));
//    }
//
//    @Override
//    public List<Expense> filterExpensesByCategory(String category) {
//        return expenses.stream()
//                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Expense> filterExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
//        return expenses.stream()
//                .filter(expense -> !expense.getDate().isBefore(startDate) && !expense.getDate().isAfter(endDate))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public double calculateTotalExpenses() {
//        return expenses.stream()
//                .mapToDouble(Expense::getAmount)
//                .sum();
//    }
//
//    @Override
//    public double calculateTotalExpensesByCategory(String category) {
//        return expenses.stream()
//                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
//                .mapToDouble(Expense::getAmount)
//                .sum();
//    }
}
