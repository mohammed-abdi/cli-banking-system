# BANKING SYSTEM

A lightweight, role-based CLI banking application with user accounts, balance management, and transaction history tracking.

## About

This project is a simple, text-based banking system built in **Java 17+**. It simulates essential banking operations through a clean console interface, supporting both regular users and administrators.

Users can manage their own accounts, perform financial operations, and review their personal transaction history, while administrators can manage system-wide data such as user accounts and full transaction history.

The application emphasizes **clarity, separation of concerns, and maintainability**, with a modular structure that cleanly separates models, repositories, UI prompts, utilities, and core logic.

## Features

### User Features
- Secure authentication with role-based dashboards
- Balance overview with formatted, color-coded output
- Deposit, withdrawal, and transfer operations
- User profile management:
  - View account info
  - Edit name and age
  - Change gender
  - Change username
  - Change password
- Personal transaction history (read-only)

### Admin Features
- Add, remove, search, and list users
- View and manage any user account
- Access full system transaction history
- Role-aware access to sensitive operations

### General
- Immutable transaction history records with unique IDs
- Append-only transaction tracking with file-based persistence
- Styled console interface for readable, structured output
- Clean menu navigation and predictable UI flows

## Specifications

- **Language:** `Java 17+`
- **Build Tool:** `Maven 3.8+`
- **Interface:** `Text-based CLI with ANSI-styled colored output`
- **Architecture:** `Modular design separating models, repositories, ui, and utils`
- **Storage:** `File-based persistence for users and transaction history`
- **Security:** `BCrypt for password hashing and validation`
- **Design Principles:**  
  `Immutability where possible, clear domain boundaries, separation of concerns, and maintainable code`

## Prerequisites

Ensure the following are installed before running the project:

- **Java 17 or higher**  
  Required to compile and run the application.

- **Maven 3.8+**  
  Used to build, package, and manage dependencies.

- **Git**  
  For cloning the repository.

- **ANSI-compatible terminal**  
  Required for proper rendering of colors and styled text.

> (Optional)
- **IDE with Maven support**  
  IntelliJ IDEA, VS Code, or Eclipse recommended.

## Installation

### Clone Repository

```bash
git clone https://github.com/mohammed-abdi/cli-banking-system.git
cd cli-banking-system
```

### License

This project is licensed under the MIT License. See [LICENSE](./LICENSE) for details.
