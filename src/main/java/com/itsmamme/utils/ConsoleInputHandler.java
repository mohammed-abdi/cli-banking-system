package com.itsmamme.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInputHandler {

    private final Scanner scanner = new Scanner(System.in);
    private static final int MIN_PASSWORD_LENGTH = 8;

    public int readInt(String message) {
        while (true) {
            if (message != null) {
                System.out.print(Message.request(message));
            }
            try {
                int value = scanner.nextInt();
                return value;
            } catch (InputMismatchException e) {
                System.out.println(Message.error("Please enter a valid number"));
                if (message == null) {
                    System.out.print(Text.color.blue(Terminal.symbol.PROMPT_MARK) + " ");
                }
            } finally {
                scanner.nextLine();
            }
        }
    }

    public int readInt(String message, int min, int max) {
        while (true) {

            int value = readInt(message);

            if (value < min || value > max) {
                System.out.println(Message.error("Value must be between " + min + " and " + max));
                if (message == null) {
                    System.out.print(Text.color.blue(Terminal.symbol.PROMPT_MARK) + " ");
                }
            } else {
                return value;

            }
        }
    }

    public int readAge(String message, int min, int max) {
        while (true) {
            System.out.print(Message.request(!message.isEmpty() ? message : "age"));
            try {
                int age = scanner.nextInt();
                if (age < min) {
                    System.out.println(Message.error("Age must be at least " + min));
                } else if (age > max) {
                    System.out.println(Message.error("Age must be at most " + max));
                } else {
                    return age;
                }
            } catch (InputMismatchException e) {
                System.out.println(Message.error("Please enter a valid age"));
            } finally {
                scanner.nextLine();
            }
        }
    }

    public String readPassword(String message) {
        while (true) {
            System.out.print(Message.request(message));
            String password = scanner.nextLine().trim();

            if (password.isEmpty()) {
                System.out.println(Message.error("Password cannot be empty"));
            } else if (password.length() < MIN_PASSWORD_LENGTH) {
                System.out.println(Message.error("Password must be at least 8 characters long"));
            } else {
                return password;
            }
        }

    }

    public String readNewPassword() {
        while (true) {
            String newPassword = readPassword("new password");
            System.out.print(Message.request("confirm password"));
            String confirmPassword = scanner.nextLine().trim();

            if (newPassword.equals(confirmPassword)) {
                return newPassword;
            }

            System.out.println(Message.error("Password don't match"));
            System.out.println(Terminal.decorator.separator());
        }
    }

}
