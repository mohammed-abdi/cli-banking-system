package com.itsmamme.utils;

public class Policy {

    protected static double MIN_WITHDRAWAL_AMOUNT = 200.00;
    protected static double MAX_WITHDRAWAL_AMOUNT = 100000.00;

    protected static double MIN_DEPOSIT_AMOUNT = 100.00;
    protected static double MAX_DEPOSIT_AMOUNT = 50000.00;

    protected static int MIN_AGE = 18;
    protected static int MAX_AGE = 90;

    protected static boolean isValid(double amount) {
        if (amount <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
