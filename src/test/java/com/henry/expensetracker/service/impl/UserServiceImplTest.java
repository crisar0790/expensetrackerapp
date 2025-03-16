package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.UserRequest;
import com.henry.expensetracker.controller.model.response.UserResponse;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.AddUserException;
import com.henry.expensetracker.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("These tests evaluate the methods of UserService")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepositoryImpl userRepositoryImpl;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private UserRequest userRequest;
    private User user;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest("John Doe", "john.doe@example.com");
        user = new User("John Doe", "john.doe@example.com");
        userResponse = new UserResponse("John Doe", "john.doe@example.com");
    }

    @DisplayName("addUser should return UserResponse when user is added successfully")
    @Test
    void addUser_ShouldReturnUserResponse_WhenUserIsAddedSuccessfully() throws AddUserException {
        when(userRepositoryImpl.addUser(any(User.class))).thenReturn(userResponse);

        UserResponse response = userServiceImpl.addUser(userRequest);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        assertEquals("john.doe@example.com", response.getEmail());

        verify(userRepositoryImpl, times(1)).addUser(any(User.class));
    }

    @DisplayName("addUser should throw Exception when the method fails")
    @Test
    void addUser_ShouldThrowException_WhenRepositoryFails() throws AddUserException {
        when(userRepositoryImpl.addUser(any(User.class))).thenThrow(new AddUserException("Error adding user"));

        AddUserException exception = assertThrows(AddUserException.class, () -> {
            userServiceImpl.addUser(userRequest);
        });

        assertEquals("Error adding user", exception.getMessage());

        verify(userRepositoryImpl, times(1)).addUser(any(User.class));
    }
}