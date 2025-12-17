package com.itsmamme.models;

import com.itsmamme.utils.Text;

public abstract class AbstractBaseUser {
    private String firstName;
    private String lastName;
    private int age;

    public AbstractBaseUser(
            String firstName,
            String lastName,
            int age) {
        setFirstName(firstName);
        setLastName(lastName);
        this.age = age;
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

    public void setFirstName(String firstName) {
        this.firstName = Text.normalizeCapitalization(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = Text.normalizeCapitalization(lastName);
    }

    public void setAge(int age) {
        this.age = age;
    }
}
