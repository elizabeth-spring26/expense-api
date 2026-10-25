package com.elizabeth.expenseapi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data writes the implementation of this interface for you.
 * JpaRepository already gives you save, findById, findAll, deleteById, and more.
 * Extra queries can be declared just by naming a method the right way.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Spring turns this method name into: SELECT * FROM expense WHERE category = ? (case-insensitive)
    List<Expense> findByCategoryIgnoreCase(String category);
}
