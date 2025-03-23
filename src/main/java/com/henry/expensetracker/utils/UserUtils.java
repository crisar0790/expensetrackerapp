package com.henry.expensetracker.utils;

import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.GetUserException;
import com.henry.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) throws GetUserException {
        try {
            return userRepository.findByEmail(email);
        } catch (EmptyResultDataAccessException e) {
            throw new GetUserException("Something was wrong getting user");
        }
    }
}
