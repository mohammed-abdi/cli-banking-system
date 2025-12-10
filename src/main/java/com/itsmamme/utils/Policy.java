package com.itsmamme.utils;

public class Policy {

    protected static double minWithdrawalAmount = 200.00;
    protected static double maxWithdrawalAmount = 100000.00;
    protected static double minDepositAmount = 100.00;
    protected static double maxDepositAmount = 50000.00;

    protected static boolean isValid(double amount) {
        if (amount <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
