package com.fullstackshopping.easyshopping.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating;


    public Products(){

    }

    //default constructor
    public Products(String name, String description, BigDecimal rating){
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    //overload in case no rating is provided
    public Products(String name, String description){
        this(name, description, BigDecimal.valueOf(0));
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    @Override
    public String toString(){
        return "Product{" +
                "\n\"id\"= \"" + this.id + "\"" +
                "\n\"name\"= \"" + this.name + "\"" +
                "\n\"description\"= \"" + this.description + "\"" +
                "\n\"rating\"= \"" + this.rating.toString() + "\"" +
                "\n}";
    }

}
