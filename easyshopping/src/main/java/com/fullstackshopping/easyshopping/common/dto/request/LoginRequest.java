package com.fullstackshopping.easyshopping.common.dto.request;

import java.io.Serializable;


/**
 * A class representing a login request.
 * Implements Serializable to allow object serialization.
 * Sent to AuthenticationController to handle login validation and grant authorizations.
 */
public class LoginRequest implements Serializable {
    private String username;
    private String password;

    public LoginRequest() {
        // Default constructor
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
