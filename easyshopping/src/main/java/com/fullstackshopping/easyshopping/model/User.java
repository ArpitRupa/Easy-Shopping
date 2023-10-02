package com.fullstackshopping.easyshopping.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;


@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

}
