package com.itsmamme.ui;

import java.text.NumberFormat;
import java.util.Locale;

import com.itsmamme.enums.Role;
import com.itsmamme.enums.Side;
import com.itsmamme.enums.Transaction;
import com.itsmamme.models.User;
import com.itsmamme.utils.Message;
import com.itsmamme.utils.Terminal;
import com.itsmamme.utils.Text;

public final class Screen {

        private Screen() {
        }

        public static void pathHeader(String[] path) {
                System.out.println();
                System.out.println(Terminal.path(path));
                System.out.println(Terminal.decorator.separator());
        }

        public static void listChoices(String[] choices) {
                for (int i = 0; i < choices.length; i++) {
                        System.out.println(Text.color.mute((i + 1) + ".") + choices[i]);
                }
                System.out.print(Message.request(Text.color.mute("choose (1-" + choices.length + ")")));
        }

        public static void home() {
                String[] choices = {
                                "login",
                                "signup",
                                "about",
                                "exit"

                };

                System.out.println();
                System.out.println(Text.style.bold(Text.color.blue("WELCOME TO BANKING SYSTEM")));
                System.out.println(Terminal.decorator.separator());

                listChoices(choices);

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
                                        .green("$" + NumberFormat.getNumberInstance(Locale.US)
                                                        .format(user.account.getBalance())));
                        System.out.println(Terminal.decorator.separator());
                } else {
                        System.out.println(Terminal.decorator.separator());
                }

                listChoices(choices);
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

                pathHeader(path);

                if (transactionType != Transaction.DEPOSIT) {
                        System.out.println(Text.color.blue("◉") + " BALANCE " + Text.color
                                        .green("$" + NumberFormat.getNumberInstance(Locale.US)
                                                        .format(user.account.getBalance())));
                        System.out.println(Terminal.decorator.separator());
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
                                "change gender",
                                "change username",
                                "change password",
                                "back"
                };

                pathHeader(path);
                listChoices(choices);
        }

        public static void about() {
                System.out.println();
                System.out.println(Text.style.bold(Text.color.blue("BANKING SYSTEM")));
                System.out.println(
                                "A lightweight, role-based CLI banking application with user accounts,\nbalance management, and administrative controls.");
                System.out.println(Text.style.underline(Text.color.blue("Contributors")));
                System.out.println(
                                Text.color.mute(Terminal.decorator.tableGlyph(Side.TOP_LEFT_CORNER))
                                                + Terminal.decorator.line(25)
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.TOP_JUNCTION))
                                                + Terminal.decorator.line(25)
                                                + Text.color.mute(
                                                                Terminal.decorator.tableGlyph(Side.TOP_RIGHT_CORNER)));

                System.out.println(
                                Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE)) + " "
                                                + Text.color.blue("NAME                    ")
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE))
                                                + Text.color.blue(" DDU ID                  ")
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE)));

                System.out.println(
                                Text.color.mute(Terminal.decorator.tableGlyph(Side.LEFT_JUNCTION))
                                                + Terminal.decorator.line(25)
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.CROSS_JUNCTION))
                                                + Terminal.decorator.line(25)
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.RIGHT_JUNCTION)));

                System.out.println(
                                Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE)) + " MOHAMMED ABDI ("
                                                + Text.color.blue("Lead") + ")    "
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE))
                                                + " DDU1600554              "
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE)));

                System.out.println(
                                Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE))
                                                + " ABDULFETAH IBRAHIM      "
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE))
                                                + " DDU1600029              "
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE)));

                System.out.println(
                                Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE))
                                                + " SENA ALEMAYEHU          "
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE))
                                                + " DDU1600663              "
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE)));

                System.out.println(
                                Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE))
                                                + " ADNAN MOHAMMED          "
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE))
                                                + " DDU1601164              "
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.VERTICAL_LINE)));

                System.out.println(
                                Text.color.mute(Terminal.decorator.tableGlyph(Side.BOTTOM_LEFT_CORNER))
                                                + Terminal.decorator.line(25)
                                                + Text.color.mute(Terminal.decorator.tableGlyph(Side.BOTTOM_JUNCTION))
                                                + Terminal.decorator.line(25)
                                                + Text.color.mute(Terminal.decorator
                                                                .tableGlyph(Side.BOTTOM_RIGHT_CORNER)));

                System.out.println(Message.info(Text.color.mute("Submission Date: ") + Text.color.blue("DD/MM/YY")));
                System.out.println(
                                Message.info(Text.color.mute("Submitted To: ") + Text.color.blue("Lec. Mikiyas M.")));
        }
}
