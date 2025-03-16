package com.henry.expensetracker.repository;

import com.henry.expensetracker.controller.model.response.UserResponse;
import com.henry.expensetracker.exception.AddUserException;
import com.henry.expensetracker.exception.GetUserException;
import com.henry.expensetracker.entity.User;

public interface UserRepository {
    UserResponse addUser(User user) throws AddUserException;

//    User getUserByEmail(String email) throws GetUserException;
}
