package com.fullstackshopping.easyshopping.productimage.model;

import com.fullstackshopping.easyshopping.product.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Arrays;

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

    @Lob // Annotation for Binary Large OBject
    @Column(name = "image_data", nullable = false, updatable = false)
    private byte[] imageData; // Change the data type to byte[]



    // Constructors, getters, setters

    // Default constructor
    public ProductImage() {
    }

    public ProductImage(byte[] imageData, Product product) {
        this.imageData = imageData;
        this.product = product;
    }

    // Getters and setters
    public int getImageId() {
        return this.imageId;
    }

    public byte[] getImageData() {
        return this.imageData;
    }
    public Product getProduct() {
        return this.product;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    //Override toString() method


    @Override
    public String toString() {
        return "ProductImage{" +
                "imageId=" + imageId +
                ", product=" + product.toString() +
                '}';
    }
}
