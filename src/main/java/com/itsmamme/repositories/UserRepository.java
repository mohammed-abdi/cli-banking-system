package com.itsmamme.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.itsmamme.enums.Gender;
import com.itsmamme.enums.Role;
import com.itsmamme.models.User;
import com.itsmamme.utils.Message;
import com.itsmamme.utils.Text;

public final class UserRepository {

    private static final File USERS_FILE = new File("users.txt");
    private static final Map<String, User> users = new HashMap<>();
    public static User currentUser;

    private UserRepository() {
    }

    private static String key(String username) {
        return username == null ? null : username.toLowerCase();
    }

    public static boolean exist(String username) {
        return users.containsKey(key(username));
    }

    public static boolean accountNumberExist(String accountNumber) {
        for (User user : users.values()) {
            if (user.account.getAccountNumber().equals(accountNumber))
                return true;
        }

        return false;
    }

    public static void save(User user, boolean showLog) {
        if (user == null)
            return;
        users.put(key(user.getUsername()), user);
        sync(showLog);
    }

    public static void delete(String username, boolean showLog) {
        if (!exist(username))
            return;

        users.remove(key(username));
        sync(showLog);
    }

    public static void patch(String username, User user, boolean showLog) {
        if (!exist(username))
            return;

        users.replace(username, user);
        sync(showLog);
    }

    public static User getUser(String username) {
        return users.get(key(username));
    }

    public static User getUserByAccNumber(String accountNumber) {
        for (User user : users.values()) {
            if (user.account.getAccountNumber().equals(accountNumber)) {
                return getUser(user.getUsername());
            }
        }

        return null;
    }

    public static Map<String, User> getUsers() {
        return users;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void sync(boolean showLog) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE))) {

            if (showLog)
                System.out.println(Message.process("Syncing repository"));
            for (User user : users.values()) {
                bw.write(user.getFirstName() + "," +
                        user.getLastName() + "," +
                        user.getAge() + "," +
                        user.getGender() + "," +
                        user.getRole() + "," +
                        user.getUsername() + "," +
                        user.getPassword() + "," +
                        user.account.getAccountNumber() + "," +
                        user.account.getBalance() + ",0");

                bw.newLine();
            }
        } catch (IOException e) {
            if (showLog)
                System.out.println(Message.error("Failed to sync repository"));
        }
    }

    public static void init(boolean showLog) {
        if (!USERS_FILE.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE))) {
                bw.write("admin,admin,0,MALE,ADMIN,admin,admin,NULL,0,1");
                bw.newLine();
            } catch (IOException e) {
                System.out.println(
                        Message.error("Failed to initialize " + Text.style.underline(Text.color.blue("users.txt"))));
            }
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {

            if (showLog)
                System.out.println(Message.process("Initializing repository"));
            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 10) {
                    continue;
                }

                String firstName = parts[0];
                String lastName = parts[1];
                int age = Integer.parseInt(parts[2]);
                Gender gender = Gender.valueOf(parts[3]);
                Role role = Role.valueOf(parts[4]);
                String username = parts[5];
                String password = parts[6];
                String accountNumber = parts[7];
                double balance = Double.parseDouble(parts[8]);

                User user = new User(firstName, lastName, age, gender, role, username, password, accountNumber, balance,
                        false);
                users.put(key(username), user);
            }
        } catch (IOException e) {
            if (showLog)
                System.out.println(Message.error("Failed to initialize repository"));
        }
    }
}
