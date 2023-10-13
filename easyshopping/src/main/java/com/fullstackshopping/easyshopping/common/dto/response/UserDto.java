package com.fullstackshopping.easyshopping.common.dto.response;

import com.fullstackshopping.easyshopping.user.model.User;
import com.fullstackshopping.easyshopping.user.role.Role;

public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Role role;

    // Constructors, getters, and setters
    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = user.getRole();
    }


    // Getters and setters (only for fields you want to expose)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
