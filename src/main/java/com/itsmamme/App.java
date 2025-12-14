package com.itsmamme;

import com.itsmamme.enums.Role;
import com.itsmamme.models.User;
import com.itsmamme.repositories.TransactionHistoryRepository;
import com.itsmamme.repositories.UserRepository;
import com.itsmamme.services.Menu;

public class App {
    public static void main(String[] args) {
        UserRepository.init();
        TransactionHistoryRepository.init();

        while (true) {
            User currentUser = UserRepository.currentUser;
            boolean userAuthenticated = currentUser != null;
            boolean isAdmin = userAuthenticated ? currentUser.getRole() == Role.ADMIN : false;

            if (userAuthenticated) {

                if (isAdmin) {
                    Menu.admin(currentUser);
                } else {
                    Menu.user(currentUser);
                }
            } else {
                boolean sessionOngoing = Menu.auth();
                if (!sessionOngoing)
                    break;
            }
        }
    }
}
