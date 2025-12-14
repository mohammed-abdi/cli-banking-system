package com.itsmamme.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.itsmamme.models.User;
import com.itsmamme.ui.Screen;
import com.itsmamme.utils.Message;
import com.itsmamme.utils.Text;

public final class Menu {
    private Menu() {
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void admin(User user) {
        int choice;

        adminLoop: while (true) {
            Screen.dashboard(user);
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = 6;
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    Prompt.createUser();
                    break;
                case 2:
                    Prompt.searchUser();
                    break;
                case 3:
                    Prompt.listUsers();
                    break;
                case 4:
                    Prompt.transactions(user);
                    break;
                case 5:
                    Prompt.logout(user.getUsername());
                    break adminLoop;
                case 6:
                    System.out.println(Message.error("Please enter a number"));
                    break;
                default:
                    System.out.println(Message.error("An unexpected error occurred. Please try again"));
                    break;
            }
        }
    }

    public static void user(User user) {
        int choice;

        userLoop: while (true) {
            Screen.dashboard(user);
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = 7;
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    Prompt.deposit(user);
                    break;
                case 2:
                    Prompt.withdraw(user);
                    break;
                case 3:
                    Prompt.transfer(user);
                    break;
                case 4:
                    Prompt.transactions(user);
                    break;
                case 5:
                    Prompt.settings(user);
                    break;
                case 6:
                    Prompt.logout(user.getUsername());
                    break userLoop;
                case 7:
                    System.out.println(Message.error("Please enter a number"));
                    break;
                default:
                    System.out.println(Message.error("An unexpected error occurred. Please try again"));
                    break;
            }
        }
    }

    public static boolean auth() {
        int choice;
        while (true) {
            Screen.home();
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = 5;
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    Prompt.login();
                    return true;
                case 2:
                    Prompt.signup();
                    return true;
                case 3:
                    Screen.about();
                    return true;
                case 4:
                    System.out.println(
                            Message.info("Program ended. " + Text.style.underline(Text.color.blue("Thank you!"))));
                    return false;
                case 5:
                    System.out.println(Message.error("Please enter a number"));
                    return true;
                default:
                    System.out.println(Message.error("An unexpected error occurred. Please try again"));
                    return true;
            }
        }
    }
}
