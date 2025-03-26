package com.henry.expensetracker.utils.impl;

import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.GetUserException;
import com.henry.expensetracker.repository.UserRepository;
import com.henry.expensetracker.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class UserUtilsImpl implements UserUtils {

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
