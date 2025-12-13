package com.itsmamme.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.itsmamme.enums.Gender;
import com.itsmamme.enums.Role;
import com.itsmamme.models.User;
import com.itsmamme.repositories.UserRepository;

public class Auth {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean checkPassword(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }

    public static boolean login(String username, String password) {
        boolean userFound = UserRepository.exist(username);

        if (!userFound)
            return false;

        User user = UserRepository.getUser(username);

        if (user.passwordMatch(password)) {
            UserRepository.setCurrentUser(user);
            return true;
        } else {
            return false;
        }
    }

    public static boolean logout() {
        if (UserRepository.currentUser == null)
            return false;

        UserRepository.currentUser = null;
        return true;
    }

    public static void signup(
            String firstName,
            String lastName,
            int age,
            Gender gender,
            Role role,
            String username,
            String password,
            String accountNumber,
            double balance) {
        User newUser = new User(firstName, lastName, age, gender, role, username, password, accountNumber, balance,
                true);

        UserRepository.save(newUser, false);
        UserRepository.setCurrentUser(newUser);
    }
}
