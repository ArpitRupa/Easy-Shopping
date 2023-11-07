package com.fullstackshopping.easyshopping.common.dto.request;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.util.Arrays;

public class ProductImageRequest {

    private int productId;
    private MultipartFile imageFile;

    public ProductImageRequest(){}

    public ProductImageRequest(int productId, MultipartFile imageFile) {
        this.productId = productId;
        this.imageFile = imageFile;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public MultipartFile getImageFile() {
        return this.imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    @Override
    public String toString() {
        return "ProductImageRequest{" +
                "productId=" + productId +
                ", imageFile=" + imageFile.getName() +
                '}';
    }

}
