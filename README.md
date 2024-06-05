# Library Management System API

## Overview

The Library Management System API is a RESTful API developed using Spring Boot to facilitate the management of books, users, and rentals in a library system. The API allows CRUD operations for books and users, with functionalities such as renting and returning books. It employs Spring Security for authentication and authorization, and uses JWT tokens for securing endpoints.

## Features

- CRUD operations for books and users
- Renting and returning books
- Role-based access control (RBAC) with Spring Security
- Password encryption using BCrypt
- Global Exception Handling
- Model mapping with ModelMapper
- Unit tests for controllers and services
- Integration with MySQL database

## Architecture

The API follows a layered architecture with the following components:

- **Controller:** Handles incoming HTTP requests and delegates them to the service layer.
- **Service:** Contains the business logic and interacts with the repository layer.
- **Repository:** Provides access to the MySQL database using Spring Data JPA.
- **Model:** Represents entities and DTOs (Data Transfer Objects) used for data exchange.
- **Security Configuration:** Configures Spring Security for authentication and authorization.

## Endpoints

- **Book Endpoints**
  - `GET /books` - Retrieve all books.
  - `GET /books/{bookId}` - Retrieve a book by ID.
  - `POST /books` - Create a new book.
  - `PUT /books/{bookId}` - Update an existing book.
  - `DELETE /books/{bookId}` - Delete a book by ID.

- **User Endpoints**
  - `GET /users` - Retrieve all users.
  - `GET /users/{userId}` - Retrieve a user by ID.
  - `POST /users/register` - Register a new user.
  - `PUT /users/{userId}` - Update an existing user.
  - `DELETE /users/{userId}` - Delete a user by ID.

- **Authentication Endpoints**
  - `POST /auth/register` - Register a new user.
  - `POST /auth/login` - Authenticate and generate JWT token.

- **Rental Endpoints**
  - `POST /rentals/rent/{bookId}` - Rent a book.
  - `POST /rentals/return/{bookId}` - Return a rented book.

## Implemented Code

### Controllers

- `BookController`, `UserController`, and `AuthController` contain endpoints for managing books, users, and authentication, respectively.

### DTOs

- `BookDto` and `UserDto` are Data Transfer Objects representing book and user entities.

### Entities

- `Book` and `User` are JPA entities representing book and user entities, respectively.

### Security Configuration

- `WebSecurityConfig` configures Spring Security for authentication and authorization.

### Services

- `BookServiceImpl`, `UserServiceImpl`, and `RentalServiceImpl` contain business logic for managing books, users, and rentals, respectively.

### Configuration

- `ModelMapperConfig` configures ModelMapper for entity-to-DTO mapping.
- `PasswordEncoderUtil` provides a bean for BCrypt password encryption.

### Repositories

- `BookRepository`, `UserRepository`, and `RentalRepository` provide CRUD operations for book, user, and rental entities, respectively.

### Unit Tests

- Basic unit tests are included for controllers and services using Mockito and JUnit.

## Tools and Technologies

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- ModelMapper
- BCryptPasswordEncoder
- H2 Database
- MySQL
- MockMvc
- Lombok
- JUnit
- Mockito

## Setup and Usage

1. Clone the repository `git clone https://github.com/nikhil1905-n/RentRead.git`.
2. Configure MySQL database settings in `application.properties`.
3. Run the application using `./gradlew bootrun` or your IDE.
4. Use endpoints to perform CRUD operations on books and users, and manage rentals.
5. Register users using the `/auth/register` endpoint.

