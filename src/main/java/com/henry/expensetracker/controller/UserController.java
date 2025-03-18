package com.henry.expensetracker.controller;

import com.henry.expensetracker.controller.model.request.UserRequest;
import com.henry.expensetracker.controller.model.response.UserResponse;
import com.henry.expensetracker.exception.AddUserException;
import com.henry.expensetracker.exception.GetUserException;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * Endpoint para agregar un nuevo usuario.
     */
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
        try {
            log.info("Adding a new user: {}", userRequest.getEmail());
            UserResponse createdUser = userService.addUser(userRequest);
            return ResponseEntity.ok(createdUser);
        } catch (AddUserException e) {
            log.error("An error occurred while trying to save the new user: {}", e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
