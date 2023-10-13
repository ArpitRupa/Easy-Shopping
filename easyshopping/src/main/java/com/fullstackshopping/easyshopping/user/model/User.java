package com.fullstackshopping.easyshopping.user.model;

import com.fullstackshopping.easyshopping.user.role.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto to work with auto increment or sequence
    @Column(nullable = false, updatable = false) // cannot update ID once set
    private int id;

    @Column(nullable = false, name = "first_name", length = 50)
    private String firstName;

    @Column(nullable = false, name="last_name", length = 50)
    private String lastName;

    @Column(nullable = false, length = 320, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 25)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    // Constructors, getters, setters, and other methods

    // Default constructor
    public User() {

    }

    public User(String firstName, String lastName, String email, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    // Getters and setters
    public int getId(){
        return  this.id;
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
        this.email = email.toLowerCase();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() { return this.role; }

    public void setRole(Role role) { this.role = role; }

    //Override toString() method
    @Override
    public String toString(){
        return "User{" +
                "\n\"id\"= \"" + this.id + "\"" +
                "\n\"first name\"= \"" + this.firstName + "\"" +
                "\n\"last name\"= \"" + this.lastName + "\"" +
                "\n\"email\"= \"" + this.email + "\"" +
                "\n\"username\"= \"" + this.username + "\"" +
                "\n\"Role\"= \"" + this.role + "\"" +
                "\n}";
    }
}