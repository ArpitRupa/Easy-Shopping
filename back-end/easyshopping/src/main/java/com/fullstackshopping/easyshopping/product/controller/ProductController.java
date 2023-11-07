package com.fullstackshopping.easyshopping.product.controller;

import com.fullstackshopping.easyshopping.common.dto.request.ProductCreationRequest;
import com.fullstackshopping.easyshopping.common.dto.request.ProductImageRequest;
import com.fullstackshopping.easyshopping.common.dto.request.ProductRequest;
import com.fullstackshopping.easyshopping.product.model.Product;
import com.fullstackshopping.easyshopping.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Product>> getProductBySellerId(@PathVariable int sellerId) {
        List<Product> products = productService.getAllProductsBySellerId(sellerId);
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreationRequest creationRequest, @RequestHeader(name="Authorization") String token) {
        Product createdProduct;

        // if list of images is not null and not empty:
        if (creationRequest.getImageRequests() != null && !creationRequest.getImageRequests().isEmpty()) {
            // call transactional service method to create images and product directly
            createdProduct = productService.createProductWithImages(creationRequest.getProductRequest(), creationRequest.getImageRequests(), token);
        } else {
            // create listing without image
            createdProduct = productService.createProduct(creationRequest.getProductRequest(), token);
        }

        System.out.println("PrintProduct: "+ createdProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody ProductRequest updatedProductRequest) {
        Product updatedProduct = productService.updateProduct(productId, updatedProductRequest);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable int productId) {
        boolean deleted = productService.deleteProduct(productId);
        if (deleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
