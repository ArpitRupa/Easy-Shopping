package com.fullstackshopping.easyshopping.order.model;

import com.fullstackshopping.easyshopping.address.model.Address;
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
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto to work with auto increment or sequence
    @Column(name = "order_id", nullable = false, updatable = false) // cannot update ID once set
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false, updatable = false)
    private User seller;


    @ManyToOne
    @JoinColumn(name = "address_id") // This column links to the "address_id" in the Address table
    private Address shippingAddress;

    @Column(name = "product_count", nullable = false)
    private int productCount;

    @CreationTimestamp
    @Column(name = "date_time", nullable = false, updatable = false)
    private LocalDateTime dateTime;

    @Column(name = "total_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCost;

    //Constructors

    //Default constructor
    public Order(){

    }

    public Order(Product product, User customer, User seller, int productCount, LocalDateTime dateTime,
                 String shippingAddressLine1, String shippingAddressLine2,
                 String city, String stateName, String postalCode)
    {
        this.product = product;
        this.customer = customer;
        this.seller = seller;
        this.productCount = productCount;
        this.dateTime = dateTime;
    }

    // getters and setters

    public int getOrderId() {
        return this.orderId;
    }

    public Product getProduct() {
        return this.product;
    }

    public User getCustomer() {
        return this.customer;
    }

    public User getSeller() {
        return this.seller;
    }

    public int getProductCount() {
        return this.productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public BigDecimal getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    //Override toString() method
    @Override
    public String toString() {
        return "Order{" +
                "\n\"orderId\"= \"" + this.orderId + "\"" +
                "\n\"product\"= \"" + this.product + "\"" +
                "\n\"customer\"= \"" + this.customer + "\"" +
                "\n\"seller\"= \"" + this.seller + "\"" +
                "\n\"productCount\"= \"" + this.productCount + "\"" +
                "\n\"dateTime\"= \"" + this.dateTime + "\"" +
                "\n}";
    }
}
