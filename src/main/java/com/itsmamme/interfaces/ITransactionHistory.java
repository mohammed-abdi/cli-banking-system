package com.itsmamme.interfaces;

import java.time.LocalDateTime;

import com.itsmamme.enums.Transaction;

public interface ITransactionHistory {
    String getId();

    Transaction getTransactionType();

    String getFromAccount();

    String getToAccount();

    double getAmount();

    LocalDateTime getTimestamp();
}
