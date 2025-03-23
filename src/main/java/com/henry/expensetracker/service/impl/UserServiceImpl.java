package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.UserRequest;
import com.henry.expensetracker.controller.model.response.UserResponse;
import com.henry.expensetracker.exception.AddUserException;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.repository.UserRepository;
import com.henry.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse addUser(UserRequest userRequest) throws AddUserException {
        User user = mapToUser(userRequest);
        userRepository.save(user);

        return mapToUserResponse(user);
    }

    private User mapToUser(UserRequest userRequest) {
        return new User(
                userRequest.getName(),
                userRequest.getEmail()
        );
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getName(),
                user.getEmail()
        );
    }
}
