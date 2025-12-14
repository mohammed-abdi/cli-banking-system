package com.itsmamme.models;

import java.time.LocalDateTime;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.itsmamme.enums.Transaction;

public class TransactionHistory {
    private final String id;
    private final Transaction transactionType;
    private final String fromAccount;
    private final String toAccount;
    private final double amount;
    private final LocalDateTime timestamp;

    public TransactionHistory(
            String id,
            Transaction transactionType,
            String fromAccount,
            String toAccount,
            double amount,
            LocalDateTime timestamp) {
        this.id = id;
        this.transactionType = transactionType;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public TransactionHistory(
            Transaction transactionType,
            String fromAccount,
            String toAccount,
            double amount) {
        this.id = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR,
                NanoIdUtils.DEFAULT_ALPHABET,
                10);
        this.transactionType = transactionType;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Transaction getTransactionType() {
        return transactionType;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
