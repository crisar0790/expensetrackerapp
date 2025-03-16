package com.henry.expensetracker.controller;

import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.exception.ExpenseNotAdded;
import com.henry.expensetracker.exception.ExpenseNotDeleted;
import com.henry.expensetracker.exception.ExpenseNotFoundException;
import com.henry.expensetracker.exception.ExpenseNotUpdated;
import com.henry.expensetracker.entity.Expense;
import com.henry.expensetracker.service.impl.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseServiceImpl expenseService;

    /**
     * Endpoint para agregar un nuevo gasto.
     * Se espera que la fecha se envíe en formato ISO (yyyy-MM-dd).
     */
    @PostMapping("/add")
    public ResponseEntity<?> addExpense(
            @RequestBody ExpenseRequest expense) {
        try {
            ExpenseResponse expenseResponse = expenseService.addExpense(expense);
            return ResponseEntity.ok(expenseResponse);
        } catch (ExpenseNotAdded e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Endpoint para listar los gastos de un usuario.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> listExpensesByUser(@PathVariable String email) {
        try {
            List<ExpenseResponse> expenses = expenseService.listExpensesByUser(email);
            return ResponseEntity.ok(expenses);
        } catch (ExpenseNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    /**
     * Endpoint para obtener un gasto por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getExpense(@PathVariable Long id) {
        try {
            ExpenseResponse expense = expenseService.getExpense(id);
            return ResponseEntity.ok(expense);
        } catch (ExpenseNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    /**
     * Endpoint para obtener todos los gastos registrados.
     */
    @GetMapping
    public ResponseEntity<?> getAllExpenses() {
        try {
            List<ExpenseResponse> expenses = expenseService.getAllExpenses();
            return ResponseEntity.ok(expenses);
        } catch (ExpenseNotFoundException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Endpoint para actualizar un gasto.
     * Se espera que la fecha se envíe en formato ISO (yyyy-MM-dd).
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(
            @PathVariable Long id,
            @RequestBody ExpenseRequest expense) {
        try {
            boolean updated = expenseService.updateExpense(expense, id);
            if (updated) {
                return ResponseEntity.ok("Gasto actualizado exitosamente.");
            } else {
                return ResponseEntity.status(404).body("Gasto no encontrado.");
            }
        } catch (ExpenseNotUpdated e) {
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Formato de fecha inválido. Utilice el formato ISO (yyyy-MM-dd).");
        }
    }

    /**
     * Endpoint para eliminar un gasto.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        try {
            boolean deleted = expenseService.deleteExpense(id);
            if (deleted) {
                return ResponseEntity.ok("Gasto eliminado exitosamente.");
            } else {
                return ResponseEntity.status(404).body("Gasto no encontrado.");
            }
        } catch (ExpenseNotDeleted e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
