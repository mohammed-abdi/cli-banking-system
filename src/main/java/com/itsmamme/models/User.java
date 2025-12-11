package com.itsmamme.models;

import com.itsmamme.enums.Gender;
import com.itsmamme.enums.Role;
import com.itsmamme.services.Auth;
import com.itsmamme.utils.Text;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private Role role;
    public Account account;
    private String username;
    private String password;

    public User(
            String firstName,
            String lastName,
            int age,
            Gender gender,
            Role role,
            String username,
            String password,
            String accountNumber,
            double balance,
            boolean hashPassword

    ) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setGender(gender);
        setRole(role);
        setUsername(username);
        setPassword(password, hashPassword);
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

    public Gender getGender() {
        return gender;
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public void setPassword(String password, boolean hashPassword) {
        if (hashPassword) {
            this.password = Auth.hashPassword(password);
        } else {
            this.password = password;
        }
    }

    public boolean passwordMatch(String password) {
        return Auth.checkPassword(password, this.password);
    }

}
