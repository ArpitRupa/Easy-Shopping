package com.fullstackshopping.easyshopping.dto.request;

import java.io.Serializable;

public class UserRegistration implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;


    // no-args constructor
    public  UserRegistration(){

    }

    // args Constructor
    public UserRegistration(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        // default to USER role
        this.role = "USER";
    }
    // Getters and setters for all fields

    public String getFirstName() { return this.firstName; }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

}
