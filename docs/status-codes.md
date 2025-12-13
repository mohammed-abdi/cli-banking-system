# Status Codes

This document defines the internal status codes returned by the `Account` domain model
for deposit and withdrawal operations.

These codes are **domain-level results**, not HTTP responses, and are used to provide
clear, consistent feedback across the application layers (CLI, services, repositories).

---

## Design Principles

- Status codes represent **business rule outcomes**
- No exceptions are thrown for expected user errors
- State mutation only occurs on successful operations
- Codes are consistent across `deposit` and `withdraw`

---

## Common Status Codes

| Code  | Meaning        | Description                                                              |
| ----- | -------------- | ------------------------------------------------------------------------ |
| `200` | Success        | The operation completed successfully and the account balance was updated |
| `400` | Invalid Amount | The provided amount is invalid (null, NaN, zero, or negative)            |
| `402` | Below Minimum  | The amount is below the minimum allowed by policy                        |
| `403` | Above Maximum  | The amount exceeds the maximum allowed by policy                         |

---

## Deposit-Specific Codes

> Returned by: `Account.deposit(double amount)`

| Code  | Meaning               | Description                                   |
| ----- | --------------------- | --------------------------------------------- |
| `200` | Deposit Successful    | Amount was added to the account balance       |
| `400` | Invalid Amount        | Amount failed basic validation                |
| `402` | Below Minimum Deposit | Amount is less than the minimum deposit limit |
| `403` | Above Maximum Deposit | Amount exceeds the maximum deposit limit      |

---

## Withdrawal-Specific Codes

> Returned by: `Account.withdraw(double amount)`

| Code  | Meaning                  | Description                                        |
| ----- | ------------------------ | -------------------------------------------------- |
| `200` | Withdrawal Successful    | Amount was deducted from the account balance       |
| `400` | Invalid Amount           | Amount failed basic validation                     |
| `402` | Below Minimum Withdrawal | Amount is less than the minimum withdrawal limit   |
| `403` | Above Maximum Withdrawal | Amount exceeds the maximum withdrawal limit        |
| `405` | Insufficient Balance     | Account balance is insufficient for the withdrawal |

---

## Notes

- These status codes are **intentionally similar** to HTTP codes for familiarity,
  but they are **not HTTP responses**
- UI layers should map these codes to **user-friendly messages**
- Logging layers should capture the code for diagnostics

---

## Usage

```java
int result = account.withdraw(amount);

if (result != 200) {
    // map result to user-facing error message
}
```
