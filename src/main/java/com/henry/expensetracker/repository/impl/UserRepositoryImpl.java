package com.henry.expensetracker.repository.impl;

import com.henry.expensetracker.controller.model.response.UserResponse;
import com.henry.expensetracker.exception.AddUserException;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final String SQL_ADD_USER = "INSERT INTO users (name, email) VALUES (?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserResponse addUser(User user) throws AddUserException {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(SQL_ADD_USER, user.getName(), user.getEmail());;

            return new UserResponse(
                    user.getName(),
                    user.getEmail()
            );
        } catch (DataAccessException e) {
            throw new AddUserException("Error adding user: " + e.getMessage());
        }
    }
}
