package com.henry.expensetracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.expensetracker.controller.model.request.ExpenseRequest;
import com.henry.expensetracker.controller.model.response.ExpenseResponse;
import com.henry.expensetracker.exception.ExpenseNotAdded;
import com.henry.expensetracker.service.impl.ExpenseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ExpenseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ExpenseServiceImpl expenseService;

    private ExpenseRequest expenseRequest;
    private ExpenseResponse expenseResponse;
    private ExpenseRequest expenseRequestBad;

    @BeforeEach
    void setUp() {
        expenseRequest = new ExpenseRequest("johndoe@example.com", 100.0, LocalDate.now(), "Food", "Lunch");
        expenseResponse = new ExpenseResponse(1L, "John Doe", 150.0, LocalDate.now(), "Food");
    }

    @Test
    public void testAddExpense_Success() throws Exception {
        // Mock de datos de entrada y respuesta esperada


        // Simulamos que el servicio devuelve la respuesta esperada
        Mockito.when(expenseService.addExpense(Mockito.any(ExpenseRequest.class)))
                .thenReturn(expenseResponse);

        // Ejecutar la solicitud POST y validar la respuesta
        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expenseRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.category").value("Food"))
                .andExpect(jsonPath("$.amount").value(150.0));

        // Verificamos que el servicio se haya llamado una vez con cualquier ExpenseRequest
        Mockito.verify(expenseService, times(1)).addExpense(Mockito.any(ExpenseRequest.class));
    }

    @Test
    public void testAddExpense_Failure() throws Exception {
        // Mockeamos el servicio para que lance una excepción
        Mockito.when(expenseService.addExpense(Mockito.any(ExpenseRequest.class)))
                .thenThrow(new ExpenseNotAdded("An error occurred while trying to save the new expense"));

        // Ejecutar la solicitud POST y validar el error
        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expenseRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("An error occurred while trying to save the new expense"));

        // Verificar que el servicio fue llamado una vez
        Mockito.verify(expenseService, times(1)).addExpense(Mockito.any(ExpenseRequest.class));
    }
}