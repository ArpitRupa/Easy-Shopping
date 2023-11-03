package com.fullstackshopping.easyshopping.productimage.controller;

import com.fullstackshopping.easyshopping.common.dto.request.ProductImageRequest;
import com.fullstackshopping.easyshopping.common.dto.response.ProductImageResponse;
import com.fullstackshopping.easyshopping.productimage.model.ProductImage;
import com.fullstackshopping.easyshopping.productimage.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product/images")
public class ProductImageController {
    private final ProductImageService productImageService;

    @Autowired
    public ProductImageController(ProductImageService productImageService){
        this.productImageService = productImageService;
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @PostMapping("")
    public ResponseEntity<ProductImageResponse> createProductImage(@RequestBody ProductImageRequest productImageRequest){
        return ResponseEntity.ok(this.productImageService.createProductImage(productImageRequest));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductImage>> getAllImagesForProduct(@PathVariable int productId) {
        return ResponseEntity.ok(this.productImageService.getAllImageByProductId(productId));
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @GetMapping("/{imageId}")
    public ResponseEntity<ProductImage> getImage(@PathVariable int imageId) {
        return ResponseEntity.ok(this.productImageService.getImageById(imageId));
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @PutMapping("/{imageId}")
    public ResponseEntity<Boolean> updateImage(@PathVariable int imageId, @RequestBody ProductImageRequest updatedImageRequest) {
        return ResponseEntity.ok(this.productImageService.updateImage(imageId, updatedImageRequest));
    }


    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Boolean> deleteImage(@PathVariable int imageId) {
        return ResponseEntity.ok(this.productImageService.deleteImage(imageId));
    }
}
