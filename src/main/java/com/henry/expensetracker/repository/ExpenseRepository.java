package com.henry.expensetracker.repository;

import com.henry.expensetracker.controller.model.response.ExpenseCategoryByUserResponse;
import com.henry.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e WHERE e.idUser = :idUser")
    List<Expense> findByIdUser(@Param("idUser") Long idUser);

    @Query("SELECT e.category, SUM(e.amount) FROM Expense e WHERE e.idUser = :idUser GROUP BY e.category")
    List<ExpenseCategoryByUserResponse> getTotalExpensesByUserGroupedByCategory(@Param("idUser") Long idUser);
}
