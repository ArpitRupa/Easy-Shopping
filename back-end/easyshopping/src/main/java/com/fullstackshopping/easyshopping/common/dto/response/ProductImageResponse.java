package com.fullstackshopping.easyshopping.common.dto.response;

import com.fullstackshopping.easyshopping.product.model.Product;

public class ProductImageResponse {

    private int id;
    private Product product;


    public ProductImageResponse() {
    }

    public ProductImageResponse(int id, Product product) {
        this.id = id;
        this.product = product;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
