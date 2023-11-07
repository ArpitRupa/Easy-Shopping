package com.fullstackshopping.easyshopping.product.model;

import com.fullstackshopping.easyshopping.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false, updatable = false)
    private User seller;

    @Column(name="avg_rating", precision = 3, scale = 2)
    private BigDecimal avgRating;

    @Column(name="price", scale = 2)
    private BigDecimal price;

    // Constructors, getters, setters, and other methods

    // Default constructor
    public Product(){

    }

    public Product(String name, String description, User seller, BigDecimal avgRating, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.avgRating = avgRating;
        this.price = price;
    }

    //overload in case no rating is provided
    public Product(String name, String description, User seller, BigDecimal price){
        this(name, description, seller, BigDecimal.valueOf(0), price);
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

    public User getSeller() {
        return this.seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
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
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", seller=" + seller +
                ", avgRating=" + avgRating +
                ", price=" + price +
                '}';
    }
}
