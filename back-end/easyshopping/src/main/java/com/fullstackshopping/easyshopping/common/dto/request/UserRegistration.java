package com.fullstackshopping.easyshopping.common.dto.request;

import java.io.Serializable;


/**
 * A class representing a UserRegistration.
 * Implements Serializable to allow object serialization.
 * Used by UserApiController and UserService to register or update a user in the database.
 */
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
    public UserRegistration(String firstName, String lastName, String email, String username, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
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
