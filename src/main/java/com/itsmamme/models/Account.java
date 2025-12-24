package com.itsmamme.models;

import com.itsmamme.utils.Policy;

public class Account extends Policy {

    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int deposit(double amount) {
        if (!isValid(amount)) {
            return 400;
        } else if (amount < MIN_DEPOSIT_AMOUNT) {
            return 402;
        } else if (amount >= MAX_DEPOSIT_AMOUNT) {
            return 403;
        } else {
            balance = balance + amount;
            return 200;
        }

    }

    public int withdraw(double amount) {
        if (!isValid(amount)) {
            return 400;
        } else if (amount < MIN_WITHDRAWAL_AMOUNT) {
            return 402;
        } else if (amount >= MAX_WITHDRAWAL_AMOUNT) {
            return 403;
        } else if (amount > balance) {
            return 405;
        } else {
            balance = balance - amount;
            return 200;
        }
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
