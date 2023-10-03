package com.fullstackshopping.easyshopping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import java.io.Serializable;

@Entity
@Table(name = "products")
public class ProductReview implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto to work with auto increment or sequence
    @Column(name="review_id", nullable = false, updatable = false) // cannot update ID once set; set to correct column
    private int id;

    // Multiple reviews can belong to one Product
    // Lazy Fetch since we don't want to fetch the entire Product Entity immediately per review
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    // Multiple review can belong to one User
    // Lazy Fetch since we don't want to fetch the entire User Entity immediately for a review
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @Column(name = "review_text", nullable = false, columnDefinition = "TEXT")
    String reviewText;

    @Column(name = "rating", columnDefinition = "INT CHECK (rating >= 1 AND rating <= 5)") // rating must be between 1-5
    private int rating;

    // Constructors, getters, setters

    // Default constructor
    public ProductReview() {
    }

    public ProductReview(Product product, User reviewer, String reviewText, int rating){
        this.product = product;
        this.reviewer = reviewer;
        this.reviewText = reviewText;
        this.rating=rating;
    }

    // Getters and setters for the fields

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() { return this.product; }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getReviewer() {
        return this.reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public String getReviewText() {
        return this.reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
