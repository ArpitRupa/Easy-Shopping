package com.fullstackshopping.easyshopping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

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

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, unique = true, length = 25)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    // Constructors, getters, setters, and other methods

    // Default constructor
    public User() {

    }

    public User(String firstName, String lastName, String email, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    // Getters and setters
    public int getId(){
        return  this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //Override toString() method
    @Override
    public String toString(){
        return "User{" +
                "\n\"id\"= \"" + this.id + "\"" +
                "\n\"first name\"= \"" + this.firstName + "\"" +
                "\n\"last name\"= \"" + this.lastName + "\"" +
                "\n\"email\"= \"" + this.email + "\"" +
                "\n\"username\"= \"" + this.username + "\"" +
                "\n}";
    }
}
