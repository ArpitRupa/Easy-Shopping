package com.fullstackshopping.easyshopping.order.model;

import com.fullstackshopping.easyshopping.address.model.Address;
import com.fullstackshopping.easyshopping.orderitem.model.OrderItem;
import com.fullstackshopping.easyshopping.product.model.Product;
import com.fullstackshopping.easyshopping.user.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto to work with auto increment or sequence
    @Column(name = "order_id", nullable = false, updatable = false) // cannot update ID once set
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "address_id") // This column links to the "address_id" in the Address table
    private Address shippingAddress;

    @CreationTimestamp
    @Column(name = "date_time", nullable = false, updatable = false)
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @Column(name = "total_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCost;

    //Constructors

    //Default constructor
    public Order(){

    }

    public Order(int orderId, User customer, Address shippingAddress, LocalDateTime dateTime, List<OrderItem> orderItems, BigDecimal totalCost) {
        this.orderId = orderId;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.dateTime = dateTime;
        this.orderItems = orderItems;
        this.totalCost = totalCost;
    }


    public int getOrderId() {
        return this.orderId;
    }

    public User getCustomer() {
        return this.customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", shippingAddress=" + shippingAddress +
                ", dateTime=" + dateTime +
                ", orderItems=" + orderItems +
                ", totalCost=" + totalCost +
                '}';
    }
}
