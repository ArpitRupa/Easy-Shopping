package com.fullstackshopping.easyshopping.productimage.model;

import com.fullstackshopping.easyshopping.product.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "product_images")
public class ProductImage implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto to work with auto increment or sequence
    @Column(name = "image_id", nullable = false, updatable = false) // cannot update ID once set
    private int imageId;

    // Multiple images can belong to one Product
    // Lazy Fetch since we don't want to reload the product for every image
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @Column(name = "image_url", nullable = false, updatable = false)
    private String imageUrl;


    // Constructors, getters, setters

    // Default constructor
    public ProductImage() {
    }

    public ProductImage(String imageUrl, Product product) {
        this.imageUrl = imageUrl;
        this.product = product;
    }

    // Getters and setters
    public int getImageId() {
        return this.imageId;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Product getProduct() {
        return this.product;
    }


    //Override toString() method
    @Override
    public String toString() {
        return "ProductImage{" +
                "\n\"imageId\"= " + this.imageId +
                "\n\"imageUrl\"= \"" + this.imageUrl + "\"" +
                "\n\"product\"= " + this.product.toString() +
                "\n}";
    }

}
