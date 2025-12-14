package com.itsmamme.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.itsmamme.enums.Role;
import com.itsmamme.enums.Transaction;
import com.itsmamme.models.TransactionHistory;
import com.itsmamme.models.User;
import com.itsmamme.utils.Message;
import com.itsmamme.utils.Text;

public final class TransactionHistoryRepository {
    private TransactionHistoryRepository() {
    }

    private static final File TRANSACTION_HISTORIES_FILE = new File("transaction-histories.txt");
    private static final List<TransactionHistory> transactionHistories = new ArrayList<>();

    public static List<TransactionHistory> getTransactionHistories(User user) {
        boolean isAdmin = user.getRole() == Role.ADMIN;

        if (isAdmin) {
            return List.copyOf(transactionHistories);
        } else {
            return List.copyOf(
                    transactionHistories.stream().filter(h -> h.getFromAccount().equalsIgnoreCase(user.getUsername()))
                            .toList());
        }
    }

    public static void save(String username, String receiverUser, double amount, Transaction transactionType) {
        TransactionHistory transactionHistory = new TransactionHistory(transactionType, username, receiverUser, amount);
        transactionHistories.add(transactionHistory);
        System.out.println(Message.info(Text.color.mute("Transaction ID ")
                + Text.style.underline(Text.color.blue(transactionHistory.getId()))));
    }

    public static void sync() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTION_HISTORIES_FILE))) {
            for (TransactionHistory transactionHistory : transactionHistories) {
                bw.write(transactionHistory.getId() + "," +
                        transactionHistory.getTransactionType() + "," +
                        transactionHistory.getFromAccount() + "," +
                        transactionHistory.getToAccount() + "," +
                        transactionHistory.getAmount() + "," +
                        transactionHistory.getTimestamp());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(Message.error("Failed to sync transaction history repository"));
        }
    }

    public static void init() {
        if (!TRANSACTION_HISTORIES_FILE.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTION_HISTORIES_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length != 6) {
                    continue;
                }

                String id = parts[0];
                Transaction transactionType = Transaction.valueOf(parts[1]);
                String fromAccount = parts[2];
                String toAccount = parts[3];
                double amount = Double.parseDouble(parts[4]);
                LocalDateTime timestamp = LocalDateTime.parse(parts[5]);

                TransactionHistory transactionHistory = new TransactionHistory(id, transactionType, fromAccount,
                        toAccount, amount, timestamp);
                transactionHistories.add(transactionHistory);
            }
        } catch (IOException e) {
            System.out.println(Message.error("Failed to initialize transaction history repository"));
        }
    }

}
