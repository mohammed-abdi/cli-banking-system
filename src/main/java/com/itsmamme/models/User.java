package com.itsmamme.models;

import com.itsmamme.services.Auth;
import com.itsmamme.utils.Text;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    public Account account;
    private String username;
    private String password;

    public User(
            String firstName,
            String lastName,
            int age,
            String username,
            String password,
            String accountNumber,
            double balance

    ) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setUsername(username);
        setPassword(password);
        account = new Account(accountNumber, balance);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public void setFirstName(String firstName) {
        this.firstName = Text.normalizeCapitalization(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = Text.normalizeCapitalization(lastName);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = Auth.hashPassword(password);
    }

    public boolean passwordMatch(String password) {
        return Auth.checkPassword(password, this.password);
    }

}
