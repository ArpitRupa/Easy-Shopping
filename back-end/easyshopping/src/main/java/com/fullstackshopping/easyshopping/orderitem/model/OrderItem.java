package com.fullstackshopping.easyshopping.orderitem.model;

import com.fullstackshopping.easyshopping.order.model.Order;
import com.fullstackshopping.easyshopping.product.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // The order to which this item belongs

    @ManyToOne
    @JoinColumn
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    // Constructors, getters, setters, and other methods

    public  OrderItem(){

    }

    public OrderItem(int id, Order order, Product product, int quantity) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }


    public int getId() {
        return this.id;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
