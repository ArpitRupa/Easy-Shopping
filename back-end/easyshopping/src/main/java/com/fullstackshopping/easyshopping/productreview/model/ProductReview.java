package com.fullstackshopping.easyshopping.productreview.model;

import com.fullstackshopping.easyshopping.product.model.Product;
import com.fullstackshopping.easyshopping.user.model.User;
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
@Table(name = "product_reviews")
public class ProductReview implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto to work with auto increment or sequence
    @Column(name="review_id", nullable = false, updatable = false) // cannot update ID once set; set to correct column
    private int id;

    // Multiple reviews can belong to one Product
    // Lazy Fetch since we don't want to fetch the entire Product Entity immediately per review
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false, updatable = false)
    private Product product;

    // Multiple review can belong to one User
    // Lazy Fetch since we don't want to fetch the entire User Entity immediately for a review
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false, updatable = false)
    private User reviewer;

    @Column(name = "review_text", nullable = false, columnDefinition = "TEXT")
    String reviewText;

    @Column(name = "rating", nullable = false, columnDefinition = "INT CHECK (rating >= 1 AND rating <= 5)") // rating must be between 1-5
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

    public Product getProduct() { return this.product; }

    public User getReviewer() {
        return this.reviewer;
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

    //Override toString() method
    @Override
    public String toString() {
        return "ProductReview{" +
                "\n\"id\"= \"" + this.id + "\"" +
                "\n\"product\"= \"" + this.product + "\"" +
                "\n\"reviewer\"= \"" + this.reviewer + "\"" +
                "\n\"reviewText\"= \"" + this.reviewText + "\"" +
                "\n\"rating\"= \"" + this.rating + "\"" +
                "\n}";
    }

}
