package com.itsmamme.ui;

import java.text.NumberFormat;
import java.util.Locale;

import com.itsmamme.enums.Role;
import com.itsmamme.enums.Transaction;
import com.itsmamme.models.User;
import com.itsmamme.utils.Message;
import com.itsmamme.utils.Terminal;
import com.itsmamme.utils.Text;

public final class Screen {

    private Screen() {
    }

    public static void home() {
        String[] choices = {
                "login",
                "about",
                "exit"

        };

        System.out.println();
        System.out.println(Text.style.bold(Text.color.blue("WELCOME TO BANKING SYSTEM")));
        System.out.println(Terminal.decorator.separator());

        for (int i = 0; i < choices.length; i++) {
            System.out.println(Text.color.mute((i + 1) + ".") + choices[i]);
        }

        System.out.print(Message.request(Text.color.mute("choose (1-" + choices.length + ")")));
    }

    public static void dashboard(User user) {
        String[] path = {
                "home",
                "dashboard"
        };
        String[] choices;

        if (user.getRole() == Role.USER) {
            choices = new String[] {
                    "deposit",
                    "withdraw",
                    "transfer",
                    "transactions",
                    "settings",
                    "logout"

            };
        } else {
            choices = new String[] {
                    "create user",
                    "search user",
                    "list users",
                    "transactions",
                    "logout"
            };
        }

        System.out.println();
        System.out.println(Terminal.path(path));
        if (user.getRole() == Role.USER) {
            System.out.println(Terminal.decorator.separator());
            System.out.println(Text.color.blue("◉") + " BALANCE  " + Text.color
                    .green("$" + NumberFormat.getNumberInstance(Locale.US).format(user.account.getBalance())));
            System.out.println(Terminal.decorator.separator());
        } else {
            System.out.println(Terminal.decorator.separator());
        }

        for (int i = 0; i < choices.length; i++) {
            System.out.println(Text.color.mute((i + 1) + ".") + choices[i]);
        }

        System.out.print(Message.request(Text.color.mute("choose (1-" + choices.length + ")")));

    }

    public static void transact(Transaction transactionType, User user) {
        String[] path;

        if (transactionType == Transaction.DEPOSIT) {
            path = new String[] {
                    "home",
                    "dashboard",
                    "deposit"

            };
        } else if (transactionType == Transaction.WITHDRAW) {
            path = new String[] {
                    "home",
                    "dashboard",
                    "withdraw"
            };
        } else {
            path = new String[] {
                    "home",
                    "dashboard",
                    "transfer"

            };
        }

        System.out.println();
        System.out.println(Terminal.path(path));
        System.out.println(Terminal.decorator.separator());
        if (transactionType != Transaction.DEPOSIT) {
            System.out.println(Text.color.blue("◉") + " BALANCE " + Text.color
                    .green("$" + NumberFormat.getNumberInstance(Locale.US).format(user.account.getBalance())));
            System.out.println(Terminal.decorator.separator());
        }

        if (transactionType != Transaction.TRANSFER) {
            System.out.print(Message.request("Enter amount"));
        } else {
            System.out.print(Message.request("Enter account number"));
        }
    }

    public static void settings() {
        String[] path = {
                "home",
                "dashboard",
                "settings"
        };
        String[] choices = {
                "view info",
                "edit name",
                "edit age",
                "edit gender",
                "edit username",
                "change password",
                "back"
        };

        System.out.println();
        System.out.println(Terminal.path(path));
        System.out.println(Terminal.decorator.separator());

        for (int i = 0; i < choices.length; i++) {
            System.out.println(Text.color.mute((i + 1) + ".") + choices[i]);
        }

        System.out.print(Message.request(Text.color.mute("choose (1-" + choices.length + ")")));
    }
}
