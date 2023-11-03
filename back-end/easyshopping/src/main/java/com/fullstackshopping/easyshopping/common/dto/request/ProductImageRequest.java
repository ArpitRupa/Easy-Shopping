package com.fullstackshopping.easyshopping.common.dto.request;

import com.fullstackshopping.easyshopping.product.model.Product;

public class ProductImageRequest {

    private int productId;
    private byte[] imageData;

    public ProductImageRequest(){}

    public ProductImageRequest(int product, byte[] imageData) {
        this.productId = product;
        this.imageData = imageData;
    }

    public int getProductId() {
        return this.productId;
    }

    public byte[] getImageData() {
        return this.imageData;
    }


}
