package com.henry.expensetracker.repository.impl;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.*;
import com.henry.expensetracker.repository.ExpenseRepository;
import com.henry.expensetracker.utils.CategoryUtils;
import com.henry.expensetracker.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final String SQL_ADD_EXPENSE = "INSERT INTO expense (description, amount, `date`, id_category, id_user, category) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SQL_GET_EXPENSE = "SELECT * FROM expense WHERE id = ?";
    private final String SQL_LIST_EXPENSE_BY_USER = "SELECT * FROM expense WHERE id_user = ?";
    private final String SQL_GET_ALL_EXPENSES = "SELECT * FROM expense";
    private final String SQL_UPDATE_EXPENSE = "UPDATE expense SET description = ?, amount = ?, `date` = ?, id_category = ?, category = ? WHERE id = ?";
    private final String SQL_DELETE_EXPENSE = "DELETE FROM expense WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserUtils userUtils;
    @Autowired
    private CategoryUtils categoryUtils;

    @Override
    public ExpenseResponse addExpense(ExpenseRequest expense) throws ExpenseNotAdded {
        try {
            Long userId = getUserId(expense.getUserEmail());
            Long categoryId = getCategoryId(expense.getCategory());
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_ADD_EXPENSE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, expense.getDescription());
                ps.setDouble(2, expense.getAmount());
                ps.setDate(3, Date.valueOf(expense.getDate()));
                ps.setLong(4, categoryId);
                ps.setLong(5, userId);
                ps.setString(6, expense.getCategory());
                return ps;
            }, keyHolder);

            return new ExpenseResponse(keyHolder.getKey().longValue(), expense.getAmount(),expense.getDate(), expense.getCategory());

        } catch (DataAccessException e) {
            throw new ExpenseNotAdded("Error adding expense: " + e.getMessage());
        } catch (GetUserException e) {
            throw new RuntimeException(e);
        }
    }

    private Long getUserId(String email) throws GetUserException {
        try {
            User user = userUtils.getUserByEmail(email);
            return user.getId();
        } catch (EmptyResultDataAccessException e) {
            throw new GetUserException("Something was wrong getting user");
        }
    }

    private Long getCategoryId(String name) throws GetCategoryException {
        try {
            return categoryUtils.getCategoryId(name);
        } catch (EmptyResultDataAccessException e) {
            throw new GetCategoryException("Category with name not found: " + name);
        }
    }

    @Override
    public List<ExpenseResponse> listExpensesByUser(String email) throws ExpenseNotFoundException {
        try {
            Long userId = getUserId(email);
            return jdbcTemplate.query(SQL_LIST_EXPENSE_BY_USER, expenseRowMapper, userId);
        } catch (DataAccessException | GetUserException e) {
            throw new ExpenseNotFoundException("Error getting user expenses with.");
        }
    }

    @Override
    public ExpenseResponse getExpense(Long id) throws ExpenseNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_EXPENSE, expenseRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new ExpenseNotFoundException("Expense with ID "+ id + " not found");
        } catch (DataAccessException e) {
            throw new ExpenseNotFoundException("Error getting expense with ID: " + id);
        }
    }

    @Override
    public List<ExpenseResponse> getAllExpenses() throws ExpenseNotFoundException {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_EXPENSES, expenseRowMapper);
        } catch (DataAccessException e) {
            throw new ExpenseNotFoundException("Error getting all expenses");
        }
    }

    @Override
    public boolean updateExpense(ExpenseRequest expense, Long id) throws ExpenseNotUpdated {
        try {
            Long categoryId = getCategoryId(expense.getCategory());
            int rowsAffected = jdbcTemplate.update(SQL_UPDATE_EXPENSE,
                    expense.getDescription(),
                    expense.getAmount(),
                    expense.getDate(),
                    categoryId,
                    expense.getCategory(),
                    id);
            if (rowsAffected == 0) {
                throw new ExpenseNotUpdated("Expense with ID: " + id + " could not be found to update.");
            }
            return true;
        } catch (DataAccessException e) {
            throw new ExpenseNotUpdated("Error updating expense with ID: " + id);
        }
    }

    @Override
    public boolean deleteExpense(Long id) throws ExpenseNotDeleted {
        try {
            int rowsAffected = jdbcTemplate.update(SQL_DELETE_EXPENSE, id);
            if (rowsAffected == 0) {
                throw new ExpenseNotDeleted("Expense with ID: " + id + " could not be found to delete.");
            }
            return true;
        } catch (DataAccessException e) {
            throw new ExpenseNotDeleted("Error deleting expense with ID: " + id);
        }
    }

    private final RowMapper<ExpenseResponse> expenseRowMapper = (rs, rowNum) ->
            new ExpenseResponse(
                    rs.getLong("id"),
                    rs.getDouble("amount"),
                    rs.getObject("date", LocalDate.class),
                    rs.getString("category")
            );
}
