package com.fullstackshopping.easyshopping.common.dto.request;

import java.util.List;

public class ProductCreationRequest {
    private ProductRequest productRequest;
    private List<ProductImageRequest> imageRequests;

    // Constructors, getters, and setters

    public ProductCreationRequest() {
    }

    public ProductCreationRequest(ProductRequest productRequest, List<ProductImageRequest> imageRequests) {
        this.productRequest = productRequest;
        this.imageRequests = imageRequests;
    }

    public ProductRequest getProductRequest() {
        return this.productRequest;
    }

    public void setProductRequest(ProductRequest productRequest) {
        this.productRequest = productRequest;
    }

    public List<ProductImageRequest> getImageRequests() {
        return this.imageRequests;
    }

    public void setImageRequests(List<ProductImageRequest> imageRequests) {
        this.imageRequests = imageRequests;
    }
}
