package com.henry.expensetracker.service.impl;

import com.henry.expensetracker.controller.model.request.UserRequest;
import com.henry.expensetracker.controller.model.response.UserResponse;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.exception.AddUserException;
import com.henry.expensetracker.repository.UserRepository;
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
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequest userRequest;
    private User user;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest("John Doe", "john.doe@example.com");
        user = new User("John Doe", "john.doe@example.com");
    }

    @Test
    void testAddUser_Success() throws AddUserException {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse response = userService.addUser(userRequest);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        assertEquals("john.doe@example.com", response.getEmail());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testAddUser_Exception() {
        doThrow(new RuntimeException("Error al guardar usuario")).when(userRepository).save(any(User.class));

        assertThrows(RuntimeException.class, () -> userService.addUser(userRequest));

        verify(userRepository, times(1)).save(any(User.class));
    }
}