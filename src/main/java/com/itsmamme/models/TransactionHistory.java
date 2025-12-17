package com.itsmamme.models;

import java.time.LocalDateTime;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.itsmamme.enums.Transaction;
import com.itsmamme.interfaces.ITransactionHistory;

public class TransactionHistory implements ITransactionHistory {
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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Transaction getTransactionType() {
        return transactionType;
    }

    @Override
    public String getFromAccount() {
        return fromAccount;
    }

    @Override
    public String getToAccount() {
        return toAccount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
