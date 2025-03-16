package com.henry.expensetracker.service;

import com.henry.expensetracker.controller.model.request.UserRequest;
import com.henry.expensetracker.controller.model.response.UserResponse;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.AddUserException;
import com.henry.expensetracker.exception.GetUserException;

public interface UserService {
    UserResponse addUser(UserRequest userRequest) throws AddUserException;
//    User getUserByEmail(String email) throws GetUserException;
}
