package com.itsmamme.models;

import java.text.NumberFormat;
import java.util.Locale;

import com.itsmamme.utils.Message;
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

    public String getAccount() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (!isValid(amount)) {
            System.out.println(Message.error(amount + " is not a valid amount"));
        } else if (amount < minDepositAmount) {
            System.out.println(Message.error(
                    NumberFormat.getNumberInstance(Locale.US).format(minDepositAmount) + " is the minimum deposit"));
        } else if (amount >= maxDepositAmount) {
            System.out.println(
                    Message.error(NumberFormat.getNumberInstance(Locale.US).format(maxDepositAmount)
                            + " is the maximum deposit, please reach out to our nearby branch"));
        } else {
            balance = balance + amount;
            System.out.println(Message.success(
                    "$" + NumberFormat.getNumberInstance(Locale.US).format(amount) + " deposited successfully"));
        }

    }

    public void withdraw(double amount) {
        if (!isValid(amount)) {
            System.out.println(Message.error(amount + " is not a valid amount"));
        } else if (amount < minWithdrawalAmount) {
            System.out.println(Message.error(
                    NumberFormat.getNumberInstance(Locale.US).format(minWithdrawalAmount)
                            + " is the minimum withdrawal"));
        } else if (amount >= maxWithdrawalAmount) {
            System.out.println(
                    Message.error(NumberFormat.getNumberInstance(Locale.US).format(maxWithdrawalAmount)
                            + " is the maximum withdrawal, please reach out to our nearby branch"));
        } else if (amount > balance) {
            System.out.println(Message.error("Your balance is insufficient"));
        } else {
            balance = balance - amount;
            System.out.println(Message.success(
                    "$" + NumberFormat.getNumberInstance(Locale.US).format(amount) + " withdrawn successfully"));
        }
    }
}
