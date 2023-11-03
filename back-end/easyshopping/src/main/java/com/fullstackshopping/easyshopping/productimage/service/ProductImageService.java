package com.fullstackshopping.easyshopping.productimage.service;

import com.fullstackshopping.easyshopping.common.dto.request.ProductImageRequest;
import com.fullstackshopping.easyshopping.common.dto.response.ProductImageResponse;
import com.fullstackshopping.easyshopping.product.model.Product;
import com.fullstackshopping.easyshopping.product.repository.ProductRepository;
import com.fullstackshopping.easyshopping.productimage.model.ProductImage;
import com.fullstackshopping.easyshopping.productimage.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository, ProductRepository productRepository) {
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
    }

    public ProductImage getImageById(int imageId) {
        return this.productImageRepository.findById(imageId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Image id not found"));
    }

    public List<ProductImage> getAllImageByProductId(int productId) {
        return  this.productImageRepository.findAllByProduct_Id(productId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Images not found for product not found"));
    }

    public ProductImageResponse createProductImage(ProductImageRequest productImageRequest) {

        Product product = productRepository.findById(productImageRequest.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found; cannot POST image."));

        ProductImage newProductImage = new ProductImage(productImageRequest.getImageData(), product);


        try {
            ProductImage savedProductImage = productImageRepository.save(newProductImage);

            return new ProductImageResponse(savedProductImage.getImageId(), savedProductImage.getProduct());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image creation failed");
        }

    }

    public Boolean updateImage(int imageId, ProductImageRequest updatedImageRequest) {
        ProductImage productImage = productImageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found for PUT request"));

        productImage.setImageData(updatedImageRequest.getImageData());

        try {
            productImageRepository.save(productImage);
            return true;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image update failed");
        }

    }

    public Boolean deleteImage(int imageId) {
        ProductImage productImage = productImageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found for DELETE request."));

        try {
            productImageRepository.delete(productImage);
            return true;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image deletion failed");
        }
    }
}
