package com.fullstackshopping.easyshopping.productimage.service;

import com.fullstackshopping.easyshopping.common.dto.request.ProductImageRequest;
import com.fullstackshopping.easyshopping.common.dto.response.ProductImageResponse;
import com.fullstackshopping.easyshopping.product.model.Product;
import com.fullstackshopping.easyshopping.product.repository.ProductRepository;
import com.fullstackshopping.easyshopping.productimage.Util.ImageProcessor;
import com.fullstackshopping.easyshopping.productimage.model.ProductImage;
import com.fullstackshopping.easyshopping.productimage.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


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

    public ProductImageResponse createProductImage(ProductImageRequest productImageRequest) throws IOException {

        Product product = productRepository.findById(productImageRequest.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found; cannot POST image."));

        byte[] imageBytes = ImageProcessor.compressImage(productImageRequest.getImageFile());
        String imageName = "image_" + UUID.randomUUID()+"_product_"+product.getId();
        String fileType = extractFileType(Objects.requireNonNull(productImageRequest.getImageFile().getContentType()));

        ProductImage newProductImage = new ProductImage(product, imageBytes, imageName, fileType);


        try {
            ProductImage savedProductImage = productImageRepository.save(newProductImage);

            return new ProductImageResponse(savedProductImage.getImageId(), savedProductImage.getProduct());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image creation failed");
        }

    }

    public Boolean updateImage(int imageId, ProductImageRequest updatedImageRequest) throws IOException {
        ProductImage productImage = productImageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found for PUT request"));

        byte[] imageBytes = updatedImageRequest.getImageFile().getBytes();
        String imageName = updatedImageRequest.getImageFile().getName();
        String fileType = updatedImageRequest.getImageFile().getContentType();

        productImage.setImageData(imageBytes);
        productImage.setFileName(imageName);
        productImage.setFileType(fileType);

        try {
            productImageRepository.save(productImage);
            return true;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image update failed");
        }

    }

    public Boolean deleteImage(int imageId) {
        try {
            productImageRepository.deleteById(imageId);
            return true;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image deletion failed for image with id: " + imageId);
        }
    }

    // Transform FileType from image/png -> png
    private String extractFileType(String fullContentType) {
        String[] parts = fullContentType.split("/");
        if (parts.length == 2) {
            return parts[1];
        } else {
            return fullContentType;
        }
    }
}
