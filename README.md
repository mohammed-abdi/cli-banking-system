# BANKING SYSTEM

A lightweight, role-based CLI banking application with user accounts, balance management, and administrative controls.

## About

This project is a simple, text-based banking system built in Java 17+. It simulates essential banking operations through a clean console interface, supporting both regular users and administrators. Users can manage their own accounts, perform financial operations, and review transaction history, while administrators can manage system-wide data such as user accounts and transaction logs.

The application focuses on clarity, separation of concerns, and maintainable structure by organizing screens, utilities, services, and repositories into dedicated, modular components.

## Features

### User Features
- Secure authentication with a role-based dashboard
- Balance overview with formatted, color-coded display
- Deposit and withdrawal operations
- User profile management (view info, update name, change password)
- Personal transaction history

### Admin Features
- Add, remove, search, and list users
- Access full system transaction logs
- View and manage any user account

### General
- Styled console interface for readable, structured output
- Clean menu navigation and predictable UI layout

## Specifications

- **Language:** `Java 17+`
- **Build Tool:** `Maven 3.8+`
- **Interface:** `Text-based CLI with ANSI-styled colored output`
- **Architecture:** `Modular design separating services, models, ui, repositories, and utils`
- **Storage:** `File-based persistence for users, and transactions`
- **Security:** `BCrypt for password hashing and validation`
- **Design Principles:** `Clean separation of concerns, readable code, maintainability, and predictable user flows`

## Prerequisites

Make sure you have the following installed before running the project:

- **Java 17 or higher**  
  Required to compile and run the application.

- **Maven 3.8+**  
  Used to build, package, and manage dependencies.

- **Git**  
  For cloning the repository.

- **ANSI-compatible terminal**  
  To correctly display colors and styled text.

> (Optional)
- **An IDE with Maven support**  
  VS Code, IntelliJ IDEA, or Eclipse recommended for development.

## Installation


#### Clone Repository

```bash
git clone https://github.com/mohammed-abdi/cli-banking-system.git
cd cli-banking-system
```

### License

This project is licensed under the MIT License. See [LICENSE](./LICENSE) for details.
