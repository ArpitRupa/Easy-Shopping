package com.fullstackshopping.easyshopping.productimage.service;

import com.fullstackshopping.easyshopping.common.dto.request.ProductImageRequest;
import com.fullstackshopping.easyshopping.common.dto.response.ProductImageResponse;
import com.fullstackshopping.easyshopping.productimage.model.ProductImage;
import com.fullstackshopping.easyshopping.productimage.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {

    private ProductImageRepository productImageRepository;

    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    public ProductImage getImageById(int imageId) {
    }

    public List<ProductImage> getAllImageByProductId(int productId) {
    }

    public ProductImageResponse createProductImage(ProductImageRequest productImageRequest) {
    }

    public Boolean updateImage(int imageId, ProductImageRequest updatedImageRequest) {
    }

    public Boolean deleteImage(int imageId) {
    }
}
