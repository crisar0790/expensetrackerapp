package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.UserRequest;
import com.henry.expensetracker.controller.model.response.UserResponse;
import com.henry.expensetracker.exception.AddUserException;
import com.henry.expensetracker.exception.GetUserException;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.repository.impl.UserRepositoryImpl;
import com.henry.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    public UserResponse addUser(UserRequest userRequest) throws AddUserException {
        User user = mapToUser(userRequest);
        return userRepositoryImpl.addUser(user);
    }

    private User mapToUser(UserRequest userRequest) {
        return new User(
                userRequest.getName(),
                userRequest.getEmail()
        );
    }
}
