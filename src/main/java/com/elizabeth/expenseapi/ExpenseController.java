package com.elizabeth.expenseapi;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The HTTP layer. Each method handles one URL + HTTP verb and returns Java
 * objects, which Spring converts to JSON automatically.
 */
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseRepository repository;

    // Spring sees this constructor and passes in the repository ("dependency injection").
    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    // GET /api/expenses            -> all expenses
    // GET /api/expenses?category=food -> only that category
    @GetMapping
    public List<Expense> list(@RequestParam(required = false) String category) {
        if (category != null) {
            return repository.findByCategoryIgnoreCase(category);
        }
        return repository.findAll();
    }

    // GET /api/expenses/3 -> one expense, or 404 if it doesn't exist
    @GetMapping("/{id}")
    public Expense get(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense " + id + " not found"));
    }

    // POST /api/expenses with a JSON body -> saves it and returns it with its new id
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense create(@Valid @RequestBody Expense expense) {
        return repository.save(expense);
    }

    // TODO (exercise 1): PUT /api/expenses/{id} -> update an existing expense.
    //   Hint: look it up like get() does, copy the fields over with the setters, then save.

    // TODO (exercise 2): DELETE /api/expenses/{id} -> delete it and return 204 No Content.
    //   Hint: @DeleteMapping, @ResponseStatus(HttpStatus.NO_CONTENT), repository.existsById / deleteById.

    // TODO (exercise 3): GET /api/expenses/summary?month=2026-10 -> total spent per category that month.
    //   Hint: java.time.YearMonth.parse(month), then filter and group with streams
    //   (Collectors.groupingBy + a BigDecimal sum), returning a Map<String, BigDecimal>.
}
