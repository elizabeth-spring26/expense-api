# Expense API

A REST API for tracking personal expenses, built with Java 21 and Spring Boot.

## Features

- Create, list, and look up expenses over HTTP with JSON
- Filter expenses by category
- Input validation (e.g. amounts must be positive; missing fields return `400 Bad Request`)
- `404 Not Found` for expenses that don't exist
- Integration tests with JUnit and MockMvc

## Tech stack

| Layer      | Tool                         |
|------------|------------------------------|
| Language   | Java 21                      |
| Framework  | Spring Boot (Web MVC)        |
| Database   | H2 (in-memory) via Spring Data JPA |
| Validation | Jakarta Bean Validation      |
| Tests      | JUnit 5, MockMvc             |
| Build      | Maven (wrapper included)     |

## Running it

Requires Java 21. No other installs needed; the Maven wrapper downloads everything else.

```
./mvnw spring-boot:run      # macOS/Linux
.\mvnw.cmd spring-boot:run  # Windows
```

The API starts at http://localhost:8080 with a few sample expenses loaded.

Run the tests:

```
.\mvnw.cmd test
```

## Endpoints

| Method | URL                                  | Description                 |
|--------|--------------------------------------|-----------------------------|
| GET    | `/api/expenses`                      | List all expenses           |
| GET    | `/api/expenses?category=food`        | List expenses in a category |
| GET    | `/api/expenses/{id}`                 | Get one expense             |
| POST   | `/api/expenses`                      | Create an expense           |

Example request:

```
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{"description": "Lunch", "amount": 12.50, "category": "food", "date": "2026-10-20"}'
```

## Project structure

```
src/main/java/com/elizabeth/expenseapi/
  Expense.java             the data model (a database table)
  ExpenseRepository.java   database access (Spring Data JPA)
  ExpenseController.java   HTTP endpoints
src/test/java/com/elizabeth/expenseapi/
  ExpenseControllerTests.java
```

## Notes

This is a learning project and uses sample data only. The database is in-memory and resets on restart,
and the server only accepts connections from the local machine. There is no authentication.

## Roadmap

- [ ] Update an expense (`PUT /api/expenses/{id}`)
- [ ] Delete an expense (`DELETE /api/expenses/{id}`)
- [ ] Monthly spending summary by category (`GET /api/expenses/summary?month=2026-10`)
- [ ] Add login with Spring Security
- [ ] Switch to PostgreSQL
