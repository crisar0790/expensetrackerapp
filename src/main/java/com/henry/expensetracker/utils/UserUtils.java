package com.henry.expensetracker.utils;

import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.GetUserException;

public interface UserUtils {
    public User getUserByEmail(String email) throws GetUserException;
}
