package com.fullstackshopping.easyshopping.common.dto.request;

import com.fullstackshopping.easyshopping.product.model.Product;

public class ProductImageRequest {

    private Product product;
    private byte[] imageData;

    public ProductImageRequest(){}

    public ProductImageRequest(Product product, byte[] imageData) {
        this.product = product;
        this.imageData = imageData;
    }

    public Product getProduct() {
        return this.product;
    }

    public byte[] getImageData() {
        return this.imageData;
    }


}
