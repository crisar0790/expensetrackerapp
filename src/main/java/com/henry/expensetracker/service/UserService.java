package com.henry.expensetracker.service;

import com.henry.expensetracker.controller.model.request.UserRequest;
import com.henry.expensetracker.controller.model.response.UserResponse;
import com.henry.expensetracker.exception.AddUserException;

public interface UserService {
    UserResponse addUser(UserRequest userRequest) throws AddUserException;
}
