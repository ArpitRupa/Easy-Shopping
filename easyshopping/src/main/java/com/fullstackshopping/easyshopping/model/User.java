package com.fullstackshopping.easyshopping.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;

    @Column(nullable = false, name = first_name)
    private String firstName;

    @Column(nullable = false, name=last_name)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public User() {

    }

    public User(String firstName, String lastName, String email, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId(){
        return  this.id;
    }

    public void setId(int id){
        this.id = id;
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
