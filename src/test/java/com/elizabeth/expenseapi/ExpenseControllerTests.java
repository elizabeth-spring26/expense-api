package com.elizabeth.expenseapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Starts the whole app and sends fake HTTP requests to it with MockMvc.
 * Run with: mvnw.cmd test
 */
@SpringBootTest
@AutoConfigureMockMvc
class ExpenseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createReturnsTheSavedExpenseWithAnId() throws Exception {
        String json = """
                {"description": "Lunch", "amount": 12.50, "category": "food", "date": "2026-10-20"}
                """;

        mockMvc.perform(post("/api/expenses").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.description").value("Lunch"));
    }

    @Test
    void createRejectsANegativeAmount() throws Exception {
        String json = """
                {"description": "Refund?", "amount": -5, "category": "food", "date": "2026-10-20"}
                """;

        mockMvc.perform(post("/api/expenses").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getReturns404ForAMissingExpense() throws Exception {
        mockMvc.perform(get("/api/expenses/99999"))
                .andExpect(status().isNotFound());
    }

    // TODO: once you've built PUT, DELETE, and /summary, add a test for each here.
}
