package com.elizabeth.expenseapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * One expense. @Entity tells JPA to store these as rows in an "expense" table.
 * The validation annotations (@NotBlank, @Positive, ...) reject bad input with a 400.
 */
@Entity
public class Expense {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String description;

    // BigDecimal, not double: doubles can't represent money exactly (0.1 + 0.2 != 0.3).
    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String category;

    @NotNull
    private LocalDate date;

    // JPA needs a no-argument constructor.
    protected Expense() {
    }

    public Expense(String description, BigDecimal amount, String category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
