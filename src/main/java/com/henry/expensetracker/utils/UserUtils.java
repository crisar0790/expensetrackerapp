package com.henry.expensetracker.utils;

import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.GetUserException;
import org.springframework.beans.factory.annotation.Autowired;
import com.henry.expensetracker.repository.impl.UserRepositoryImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    private final String SQL_GET_USER = "SELECT * FROM users WHERE email = ?";

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserByEmail(String email) throws GetUserException {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_USER, new Object[]{email}, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new GetUserException("Something was wrong getting user");
        }
    }

    RowMapper<User> userRowMapper = (rs, rowNum) -> {
        return new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
        );
    };
}
