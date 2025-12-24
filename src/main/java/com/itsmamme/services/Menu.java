package com.itsmamme.services;

import com.itsmamme.models.User;
import com.itsmamme.ui.Screen;
import com.itsmamme.utils.ConsoleInputHandler;
import com.itsmamme.utils.Message;
import com.itsmamme.utils.Text;

public final class Menu {
    private Menu() {
    }

    private static final ConsoleInputHandler input = new ConsoleInputHandler();

    public static void admin(User user) {
        adminLoop: while (true) {
            Screen.dashboard(user);
            int choice = input.readInt(null, 1, 5);

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
                default:
                    System.out.println(Message.error("An unexpected error occurred. Please try again"));
                    break;
            }
        }
    }

    public static void user(User user) {
        userLoop: while (true) {
            Screen.dashboard(user);
            int choice = input.readInt(null, 1, 6);

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
                default:
                    System.out.println(Message.error("An unexpected error occurred. Please try again"));
                    break;
            }
        }
    }

    public static boolean auth() {
        while (true) {
            Screen.home();
            int choice = input.readInt(null, 1, 4);

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
                default:
                    System.out.println(Message.error("An unexpected error occurred. Please try again"));
                    return true;
            }
        }
    }
}
