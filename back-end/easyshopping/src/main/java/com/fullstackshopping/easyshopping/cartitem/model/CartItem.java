package com.fullstackshopping.easyshopping.cartitem.model;

import com.fullstackshopping.easyshopping.cart.model.Cart;
import com.fullstackshopping.easyshopping.product.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false) // cannot update ID once set
    private int id;

    @ManyToOne
    private Product product;

    private int quantity;

    @ManyToOne
    private Cart cart;

    public CartItem() {
    }

    public CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }



    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", cart=" + cart +
                '}';
    }

}