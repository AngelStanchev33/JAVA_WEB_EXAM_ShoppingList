# Shopping List Application

A Spring Boot web application for managing shopping lists. This application allows users to create and manage their shopping lists, add products, and track their shopping needs.

## Features

- User Authentication
  - User registration
  - User login/logout
  - Session management

- Shopping List Management
  - Create shopping lists
  - Add products to lists
  - Track product details (name, description, price, needed before date)
  - View and manage shopping lists

## Technical Stack

- Java 17
- Spring Boot 3.4.5
- Spring Security
- Spring Data JPA
- MySQL Database
- Thymeleaf
- Bootstrap
- Lombok
- ModelMapper

## Database Configuration

The application uses MySQL database with the following configuration:
- Database name: shopping_list
- Username: root
- Password: 12345

## Running the Application

1. Make sure you have Java 17 and MySQL installed
2. Create a MySQL database named 'shopping_list'
3. Run the application using:
   ```bash
   ./gradlew bootRun
   ```
4. Access the application at: http://localhost:8080

## Project Structure

- `src/main/java/bg/softuni/shoppinglist/`
  - `config/` - Configuration classes
  - `entity/` - JPA entities
  - `model/` - DTOs and view models
  - `repository/` - JPA repositories
  - `security/` - Security configuration and session management
  - `service/` - Business logic
  - `web/` - Controllers and web-related classes

## Security

The application implements Spring Security for:
- User authentication
- Session management
- Role-based access control
- Password encryption using BCrypt 