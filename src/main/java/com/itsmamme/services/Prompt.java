package com.itsmamme.services;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import com.itsmamme.enums.Gender;
import com.itsmamme.enums.Role;
import com.itsmamme.enums.Transaction;
import com.itsmamme.models.User;
import com.itsmamme.repositories.UserRepository;
import com.itsmamme.ui.Screen;
import com.itsmamme.utils.Message;
import com.itsmamme.utils.Policy;
import com.itsmamme.utils.Terminal;
import com.itsmamme.utils.Text;

public final class Prompt extends Policy {
    private static final Scanner scanner = new Scanner(System.in);

    private Prompt() {
    }

    public static void unexpectedError() {
        System.out.println(Message.error("An unexpected error occurred. Please try again"));
    }

    public static boolean confirm(String message) {
        System.out.print(Message.request(message + Text.color.mute(" (y/n)")));
        String choice = scanner.next().trim();

        if (choice.equalsIgnoreCase("y"))
            return true;

        return false;
    }

    public static void login() {
        String[] path = {
                "home",
                "login",
        };

        Screen.pathHeader(path);

        int attempts = 3;

        while (true) {
            if (attempts == 0) {
                System.out.println(Message.process("Redirecting to homepage"));
                break;
            }

            System.out.print(Message.request("username"));
            String username = scanner.next().trim();
            System.out.print(Message.request("password"));
            String password = scanner.next().trim();

            if (Auth.login(username, password)) {
                System.out.println(Message.success("Logged in successfully"));
                System.out.println(Message.process("Redirecting to dashboard"));
                break;
            } else {
                System.out.println(Message.error("Incorrect username or password"));
                if (attempts > 1)
                    System.out.println(Text.color.mute("◉ ") + (attempts - 1)
                            + Text.color.mute(" attempt" + (attempts > 1 ? "s" : "") + " left"));
                System.out.println(Terminal.decorator.separator());

            }

            attempts--;
        }
    }

    public static void logout(String username) {
        String[] path = {
                "home",
                "dashboard",
                "logout"
        };

        Screen.pathHeader(path);
        if (confirm("logout as " + Text.style.underline(Text.color.blue(username)))) {
            System.out.println(Message.process("Logging out"));
            Auth.logout();
        }
    }

    public static void createUser() {
        String[] path = {
                "home",
                "dashboard",
                "create-user"
        };

        Screen.pathHeader(path);
        System.out.print(Message.request("first name"));
        String firstName = scanner.next().trim();
        System.out.print(Message.request("last name"));
        String lastName = scanner.next().trim();
        int age;
        while (true) {
            System.out.print(Message.request("age"));
            try {
                age = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(Message.error("Invalid age"));
                System.out.println(Terminal.decorator.separator());
            }
        }
        boolean genderChoice = confirm("male");
        Gender gender = genderChoice ? Gender.MALE : Gender.FEMALE;
        boolean roleChoice = confirm("admin");
        Role role = roleChoice ? Role.ADMIN : Role.USER;
        String username;
        while (true) {
            System.out.print(Message.request("username"));
            username = scanner.next().trim();

            if (!UserRepository.exist(username))
                break;

            System.out.println(Message.error("Username already taken"));
            System.out.println(Terminal.decorator.separator());
        }
        String password;
        while (true) {
            System.out.print(Message.request("password"));
            String newPassword = scanner.next().trim();
            System.out.print(Message.request("confirm password"));
            String confirmPassword = scanner.next().trim();

            if (newPassword.equals(confirmPassword)) {
                password = newPassword;
                break;
            }

            System.out.println(Message.error("Password don't match"));
            System.out.println(Terminal.decorator.separator());
        }
        String accountNumber;
        while (true) {
            System.out.print(Message.request("account number"));
            accountNumber = scanner.next().trim();

            if (!UserRepository.accountNumberExist(accountNumber))
                break;

            System.out.println(Message.error("account number already taken"));
            System.out.println(Terminal.decorator.separator());
        }
        System.out.print(Message.request("initial balance"));
        double balance = scanner.nextDouble();
        User newUser = new User(firstName, lastName, age, gender, role, username, password, accountNumber, balance,
                true);
        System.out.println(Message.success("User created successfully"));
        UserRepository.save(newUser, true);
    }

    public static void signup() {
        String[] path = {
                "home",
                "signup",
        };

        Screen.pathHeader(path);
        System.out.print(Message.request("first name"));
        String firstName = scanner.next().trim();
        System.out.print(Message.request("last name"));
        String lastName = scanner.next().trim();
        int age;
        while (true) {
            System.out.print(Message.request("age"));
            try {
                age = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(Message.error("Invalid age"));
                System.out.println(Terminal.decorator.separator());
            }
        }
        boolean genderChoice = confirm("male");
        Gender gender = genderChoice ? Gender.MALE : Gender.FEMALE;
        Role role = Role.USER;
        String username;
        while (true) {
            System.out.print(Message.request("username"));
            username = scanner.next().trim();

            if (!UserRepository.exist(username))
                break;

            System.out.println(Message.error("Username already taken"));
            System.out.println(Terminal.decorator.separator());
        }
        String password;
        while (true) {
            System.out.print(Message.request("password"));
            String newPassword = scanner.next().trim();
            System.out.print(Message.request("confirm password"));
            String confirmPassword = scanner.next().trim();

            if (newPassword.equals(confirmPassword)) {
                password = newPassword;
                break;
            }

            System.out.println(Message.error("Password don't match"));
            System.out.println(Terminal.decorator.separator());
        }
        String accountNumber;
        while (true) {
            System.out.print(Message.request("account number"));
            accountNumber = scanner.next().trim();

            if (!UserRepository.accountNumberExist(accountNumber))
                break;

            System.out.println(Message.error("account number already taken"));
            System.out.println(Terminal.decorator.separator());
        }
        double balance = 0;
        Auth.signup(firstName, lastName, age, gender, role, username, password, accountNumber, balance);
        System.out.println(Message.success("Signed up successfully"));
    }

    public static void userInfo(User user) {
        System.out.println(Text.color.mute(Terminal.symbol.RIGHT_ARROW + " name:      ")
                + Text.style.underline(Text.color.blue(user.getFirstName() + " "
                        + user.getLastName())));
        System.out.println(Text.color.mute(Terminal.symbol.RIGHT_ARROW + " age:       ")
                + Text.style.underline(Text.color.blue(String.valueOf(user.getAge()))));
        System.out.println(Text.color.mute(Terminal.symbol.RIGHT_ARROW + " gender:    ")
                + Text.style.underline(Text.color.blue(String.valueOf(user.getGender()))));
        if (user.getRole() == Role.ADMIN)
            System.out.println(Text.color.mute(Terminal.symbol.RIGHT_ARROW + " role:      ")
                    + Text.style.underline(Text.color.blue(String.valueOf(user.getRole()))));
        System.out.println(Text.color.mute(Terminal.symbol.RIGHT_ARROW + " username:  ")
                + Text.style.underline(Text.color.blue(user.getUsername())));
        System.out.println(
                Text.color.mute(Terminal.symbol.RIGHT_ARROW + " account:   ")
                        + Text.style.underline(Text.color.blue(user.account.getAccountNumber())));
    }

    public static void viewInfo(User user) {
        String[] path = {
                "home",
                "dashboard",
                "settings",
                "info"
        };

        Screen.pathHeader(path);
        userInfo(user);
    }

    public static void editName(User user) {
        String[] path = {
                "home",
                "dashboard",
                "settings",
                "name"
        };

        Screen.pathHeader(path);
        System.out.println(Text.color.mute(Terminal.symbol.RIGHT_ARROW + " current name: ")
                + Text.style.underline(Text.color.blue(user.getFirstName() + " " + user.getLastName())));
        System.out.println(Terminal.decorator.separator());
        System.out.print(Message.request("first name"));
        String firstName = scanner.next().trim();
        System.out.print(Message.request("last name"));
        String lastName = scanner.next().trim();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        System.out.println(Message.success("Name changed to " + Text.style.underline(Text.color
                .blue(Text.normalizeCapitalization(firstName) + " " + Text.normalizeCapitalization(lastName)))
                + " successfully"));
        UserRepository.sync();
    }

    public static void editAge(User user) {
        String[] path = {
                "home",
                "dashboard",
                "settings",
                "age"
        };

        Screen.pathHeader(path);
        System.out.println(Text.color.mute(Terminal.symbol.RIGHT_ARROW + " current age: ")
                + Text.style.underline(Text.color.blue(String.valueOf(user.getAge()))));
        System.out.println(Terminal.decorator.separator());

        int age;
        while (true) {
            System.out.print(Message.request("age"));
            try {
                age = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(Message.error("Invalid age"));
                System.out.println(Terminal.decorator.separator());
            }
        }

        int prevAge = user.getAge();
        user.setAge(age);

        System.out.println(Message
                .success("Age changed " + Text.color.mute(String.valueOf(prevAge)) + " "
                        + Terminal.symbol.RIGHT_ARROW
                        + " "
                        + Text.color.blue(String.valueOf(age)) + " successfully"));
        UserRepository.sync();

    }

    public static void changeGender(User user) {
        String[] path = {
                "home",
                "dashboard",
                "settings",
                "gender"
        };

        Screen.pathHeader(path);
        System.out.println(Text.color.mute(Terminal.symbol.RIGHT_ARROW + " current gender: ")
                + Text.style.underline(Text.color.blue(String.valueOf(user.getGender()))));
        System.out.println(Terminal.decorator.separator());

        boolean genderChoice = confirm("male");
        Gender gender = genderChoice ? Gender.MALE : Gender.FEMALE;

        user.setGender(gender);
        System.out.println(Message.success("Gender changed successfully"));
        UserRepository.sync();
    }

    public static void changeUsername(User user) {
        String[] path = {
                "home",
                "dashboard",
                "settings",
                "username"
        };

        Screen.pathHeader(path);
        System.out.println(Text.color.mute(Terminal.symbol.RIGHT_ARROW + " current username: ")
                + Text.style.underline(Text.color.blue(user.getUsername())));
        System.out.println(Terminal.decorator.separator());

        String username;
        while (true) {
            System.out.print(Message.request("username"));
            username = scanner.next().trim();

            if (!UserRepository.exist(username))
                break;

            System.out.println(Message.error("Username in use"));
            System.out.println(Terminal.decorator.separator());
        }

        String prevUsername = user.getUsername();

        if (UserRepository.changeKey(prevUsername, username)) {
            System.out.println(Message
                    .success("Username changed to " + Text.style.underline(Text.color.blue(username))
                            + " successfully"));
            UserRepository.setCurrentUser(user);
            UserRepository.sync();
        } else {
            unexpectedError();
        }

    }

    public static void changePassword(User user) {
        String[] path = {
                "home",
                "dashboard",
                "settings",
                "password"
        };

        Screen.pathHeader(path);

        System.out.print(Message.request("old password"));
        String oldPassword = scanner.next().trim();
        if (!user.passwordMatch(oldPassword)) {
            System.out.println(Message.error("Incorrect password"));
            return;
        }

        System.out.println(Terminal.decorator.separator());
        String password;

        while (true) {
            System.out.print(Message.request("new password"));
            String newPassword = scanner.next().trim();
            System.out.print(Message.request("confirm password"));
            String confirmPassword = scanner.next().trim();

            if (newPassword.equals(confirmPassword)) {
                password = newPassword;
                break;
            }

            System.out.println(Message.error("Password don't match"));
            System.out.println(Terminal.decorator.separator());
        }

        user.setPassword(password, true);
        System.out.println(Message.success("Password changed successfully"));
        UserRepository.sync();
    }

    public static void listUsers() {

        User[] users = UserRepository.getUsers();

        String targetElement = "users" + Text.color.mute(" (" + String.valueOf(users.length) + ")");

        String[] path = {
                "home",
                "dashboard",
                targetElement
        };

        Screen.pathHeader(path);
        for (User user : users) {
            userInfo(user);
            System.out.println(Terminal.decorator.separator());
        }
    }

    public static void settings(User user) {
        while (true) {
            Screen.settings();
            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Message.error("Please enter a number "));
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    viewInfo(user);
                    break;
                case 2:
                    editName(user);
                    break;
                case 3:
                    editAge(user);
                    break;
                case 4:
                    changeGender(user);
                    break;
                case 5:
                    changeUsername(user);
                    System.out.println(Message.process("Redirecting to dashboard"));
                    return;
                case 6:
                    changePassword(user);
                    break;
                case 7:
                    System.out.println(Message.process("Redirecting to dashboard"));
                    return;
                default:
                    unexpectedError();
                    break;
            }

        }
    }

    public static void searchUser() {

        String[] path = {
                "home",
                "dashboard",
                "search"
        };

        String[] choices = {
                "edit name",
                "edit age",
                "change gender",
                "change username",
                "change password",
                "delete user",
                "back"
        };

        Screen.pathHeader(path);

        while (true) {
            System.out.print(Message.request("Username"));
            String username = scanner.next();

            User user = UserRepository.getUser(username);

            if (user != null) {
                boolean confirmed = confirm("User found: " + Text.style.underline(
                        Text.color.blue(user.getFirstName() + " " + user.getLastName()) + ". Continue"));

                if (!confirmed)
                    continue;

                while (true) {
                    user = UserRepository.getUser(username);
                    String[] userPath = {
                            "home",
                            "dashboard",
                            "users",
                            user.getUsername()
                    };

                    Screen.pathHeader(userPath);
                    userInfo(user);
                    System.out.println(Terminal.decorator.separator());
                    Screen.listChoices(choices);

                    int choice;

                    try {
                        choice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println(Message.error("Please enter a number"));
                        scanner.nextLine();
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            editName(user);
                            break;
                        case 2:
                            editAge(user);
                            break;
                        case 3:
                            changeGender(user);
                            break;
                        case 4:
                            changeUsername(user);
                            System.out.println(Message.process("Redirecting to dashboard"));
                            return;
                        case 5:
                            changePassword(user);
                            break;
                        case 6:
                            if (!confirm("Are you sure you want to delete " + Text.style
                                    .underline(Text.color.blue(user.getFirstName() + " " + user.getLastName()))))
                                break;
                            if (!UserRepository.delete(username))
                                return;
                            System.out.println(Message.success("User deleted successfully"));
                            System.out.println(Message.process("Redirecting to dashboard"));
                            return;
                        case 7:
                            System.out.println(Message.process("Redirecting to dashboard"));
                            return;
                        default:
                            unexpectedError();
                            break;
                    }
                }

            } else {
                System.out.println(Message.error(
                        "User with username " + Text.style.underline(Text.color.blue(username)) + " was not found"));
                break;
            }

        }
    }

    public static void deposit(User user) {
        Screen.transact(Transaction.DEPOSIT, user);
        double amount;

        depositLoop: while (true) {
            System.out.print(Message.request("Enter amount"));
            try {
                amount = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(Message.error("Please enter a number"));
                scanner.nextLine();
                continue;
            }

            int statusCode = user.account.deposit(amount);

            switch (statusCode) {
                case 400:
                    System.out.println(Message.error(amount + " is not a valid amount"));
                    break;

                case 402:
                    System.out.println(Message.error(
                            NumberFormat.getNumberInstance(Locale.US).format(minDepositAmount)
                                    + " is the minimum deposit"));
                    break;

                case 403:
                    System.out.println(
                            Message.error(NumberFormat.getNumberInstance(Locale.US).format(maxDepositAmount)
                                    + " is the maximum deposit, please reach out to our nearby branch"));
                    break;

                case 200:
                    System.out.println(Message.success(
                            "$" + NumberFormat.getNumberInstance(Locale.US).format(amount)
                                    + " deposited successfully"));

                    UserRepository.sync();
                    break depositLoop;

                default:
                    unexpectedError();
                    break;
            }
        }
    }

    public static void withdraw(User user) {
        Screen.transact(Transaction.WITHDRAW, user);
        double amount;

        withdrawLoop: while (true) {
            System.out.print(Message.request("Enter amount"));
            try {
                amount = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(Message.error("Please enter a number"));
                scanner.nextLine();
                continue;
            }

            int statusCode = user.account.withdraw(amount);

            switch (statusCode) {
                case 400:
                    System.out.println(Message.error(amount + " is not a valid amount"));
                    break;

                case 402:
                    System.out.println(Message.error(
                            NumberFormat.getNumberInstance(Locale.US).format(minWithdrawalAmount)
                                    + " is the minimum withdrawal"));
                    break;

                case 403:
                    System.out.println(
                            Message.error(NumberFormat.getNumberInstance(Locale.US).format(maxWithdrawalAmount)
                                    + " is the maximum withdrawal, please reach out to our nearby branch"));
                    break;

                case 405:
                    System.out.println(Message.error("Your balance is insufficient"));
                    break;

                case 200:
                    System.out.println(Message.success(
                            "$" + NumberFormat.getNumberInstance(Locale.US).format(amount)
                                    + " withdrawn successfully"));
                    UserRepository.sync();
                    break withdrawLoop;

                default:
                    unexpectedError();
                    break;
            }
        }
    }

    public static void transfer(User user) {
        Screen.transact(Transaction.TRANSFER, user);
        String accountNumber;
        User receiver;
        accountLoop: while (true) {
            System.out.print(Message.request("Enter account number"));
            accountNumber = scanner.next().trim();
            boolean ownAccount = user.account.getAccountNumber().equalsIgnoreCase(accountNumber);
            if (ownAccount) {
                System.out.println(Message.error("The destination account must be different from your own."));
            } else if (UserRepository.accountNumberExist(accountNumber)
                    && !ownAccount) {
                receiver = UserRepository.getUserByAccNumber(accountNumber);
                System.out.println(Terminal.decorator.separator());
                System.out.println(Text.color.blue("◉") + " RECEIVER  " + Text.style.underline(
                        Text.color.blue((receiver.getFirstName() + " " + receiver.getLastName()).toUpperCase())));
                System.out.println(Terminal.decorator.separator());
                break accountLoop;
            } else {
                System.out.println(Message.error(
                        "We couldn't find an account with that number. Please check and try again"));
                return;
            }
        }

        double amount;

        transferLoop: while (true) {
            System.out.print(Message.request("Enter amount"));
            try {
                amount = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(Message.error("Please enter a number"));
                scanner.nextLine();
                continue;
            }

            User sender = UserRepository.getUser(user.getUsername());

            double senderBalanceBefore = sender.account.getBalance();

            int senderStatusCode = sender.account.withdraw(amount);

            switch (senderStatusCode) {
                case 400:
                    System.out.println(Message.error(amount + " is not a valid amount"));
                    break;

                case 402:
                    System.out.println(Message.error(
                            NumberFormat.getNumberInstance(Locale.US).format(minWithdrawalAmount)
                                    + " is the minimum transfer amount"));
                    break;

                case 403:
                    System.out.println(
                            Message.error(NumberFormat.getNumberInstance(Locale.US).format(maxWithdrawalAmount)
                                    + " is the maximum transfer amount, please reach out to our nearby branch"));
                    break;

                case 405:
                    System.out.println(Message.error("Your balance is insufficient"));
                    break;

                case 200:
                    int receiverStatusCode = receiver.account.deposit(amount);

                    if (receiverStatusCode != 200) {
                        sender.account.setBalance(senderBalanceBefore);
                        System.out.println(Message.error("Transfer failed. Changes were rolled back"));
                        break transferLoop;
                    }

                    System.out.println(Message.success(
                            "$" + NumberFormat.getNumberInstance(Locale.US).format(amount)
                                    + " is transferred to "
                                    + Text.style.underline(
                                            Text.color.blue(receiver.getFirstName() + " " + receiver.getLastName()))
                                    + " successfully"));
                    UserRepository.sync();
                    break transferLoop;

                default:
                    unexpectedError();
                    break;
            }
        }
    }
}
