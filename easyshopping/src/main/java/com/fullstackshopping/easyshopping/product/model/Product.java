package com.fullstackshopping.easyshopping.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto to work with auto increment or sequence
    @Column(nullable = false, updatable = false) // cannot update ID once set
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name="avg_rating", precision = 3, scale = 2)
    private BigDecimal avgRating;

    @Column(name="price", scale = 2)
    private BigDecimal price;

    // Constructors, getters, setters, and other methods

    // Default constructor
    public Product(){

    }

    public Product(String name, String description, BigDecimal rating){
        this.name = name;
        this.description = description;
        this.avgRating = rating;
    }

    //overload in case no rating is provided
    public Product(String name, String description){
        this(name, description, BigDecimal.valueOf(0));
    }

    // Getters and setters

    public int getId() {
        return id;
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

    public BigDecimal getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(BigDecimal avgRating) {
        this.avgRating = avgRating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //Override toString() method
    @Override
    public String toString(){
        return "Product{" +
                "\n\"id\"= \"" + this.id + "\"" +
                "\n\"name\"= \"" + this.name + "\"" +
                "\n\"description\"= \"" + this.description + "\"" +
                "\n\"rating\"= \"" + this.avgRating.toString() + "\"" +
                "\n\"price\"= \"" + this.price.toString() + "\"" +
                "\n}";
    }

}
