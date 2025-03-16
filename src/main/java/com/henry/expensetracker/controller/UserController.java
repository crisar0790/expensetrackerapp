package com.henry.expensetracker.controller;

import com.henry.expensetracker.controller.model.request.UserRequest;
import com.henry.expensetracker.controller.model.response.UserResponse;
import com.henry.expensetracker.exception.AddUserException;
import com.henry.expensetracker.exception.GetUserException;
import com.henry.expensetracker.entity.User;
import com.henry.expensetracker.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * Endpoint para agregar un nuevo usuario.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
        try {
            UserResponse createdUser = userService.addUser(userRequest);
            return ResponseEntity.ok(createdUser);
        } catch (AddUserException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Endpoint para obtener un usuario por email.
     */
//    @GetMapping("/byEmail")
//    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
//        try {
//            User user = userService.getUserByEmail(email);
//            return ResponseEntity.ok(user);
//        } catch (GetUserException e) {
//            return ResponseEntity.status(404).body(e.getMessage());
//        }
//    }
}
