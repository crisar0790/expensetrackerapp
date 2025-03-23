# Expense Tracker API Documentation

## Overview
The Expense Tracker API provides endpoints for managing users, categories, and expenses. It allows users to add expenses, retrieve them, update, and delete them while maintaining category associations.

## Base URL
```
http://localhost:8080/api
```

## Endpoints

### User Endpoints
#### Add a New User
```
POST /api/users
```
**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "securepassword"
}
```
**Responses:**
- `200 OK` - User created successfully.
- `500 Internal Server Error` - Error occurred while creating user.

---

### Category Endpoints
#### Add a New Category
```
POST /api/categories
```
**Request Body:**
```json
{
  "name": "Food"
}
```
**Responses:**
- `200 OK` - Category created successfully.
- `500 Internal Server Error` - Error occurred while creating category.

#### Get All Categories
```
GET /api/categories
```
**Responses:**
- `200 OK` - Returns a list of categories.
- `500 Internal Server Error` - Error occurred while retrieving categories.

---

### Expense Endpoints
#### Add a New Expense
```
POST /api/expenses
```
**Request Body:**
```json
{
  "userEmail": "user@example.com",
  "amount": 100.50,
  "date": "2024-03-23",
  "category": "Food",
  "description": "Lunch at a restaurant"
}
```
**Responses:**
- `200 OK` - Expense added successfully.
- `500 Internal Server Error` - Error occurred while adding expense.

#### Get Expenses by User
```
GET /api/expenses/user/{email}
```
**Path Parameters:**
- `email` (string) - User's email address.

**Responses:**
- `200 OK` - Returns a list of expenses for the user.
- `404 Not Found` - No expenses found for the user.

#### Get Expense by ID
```
GET /api/expenses/{id}
```
**Path Parameters:**
- `id` (integer) - Expense ID.

**Responses:**
- `200 OK` - Returns expense details.
- `404 Not Found` - Expense not found.

#### Get All Expenses
```
GET /api/expenses
```
**Responses:**
- `200 OK` - Returns a list of all expenses.
- `500 Internal Server Error` - Error occurred while retrieving expenses.

#### Update an Expense
```
PUT /api/expenses/{id}
```
**Path Parameters:**
- `id` (integer) - Expense ID.

**Request Body:**
```json
{
  "userEmail": "user@example.com",
  "amount": 120.00,
  "date": "2024-03-24",
  "category": "Groceries",
  "description": "Supermarket shopping"
}
```
**Responses:**
- `200 OK` - Expense updated successfully.
- `400 Bad Request` - Invalid date format.
- `404 Not Found` - Expense not found.
- `500 Internal Server Error` - Error occurred while updating expense.

#### Delete an Expense
```
DELETE /api/expenses/{id}
```
**Path Parameters:**
- `id` (integer) - Expense ID.

**Responses:**
- `200 OK` - Expense deleted successfully.
- `404 Not Found` - Expense not found.
- `500 Internal Server Error` - Error occurred while deleting expense.

---

## Error Handling
Errors return a JSON response with an error message:
```json
{
  "error": "Description of the error"
}
```

## Notes
- Date format should be `yyyy-MM-dd`.
- User must exist before adding expenses.
- Categories must exist before associating them with expenses.

This API allows easy management of expenses, users, and categories for financial tracking.

