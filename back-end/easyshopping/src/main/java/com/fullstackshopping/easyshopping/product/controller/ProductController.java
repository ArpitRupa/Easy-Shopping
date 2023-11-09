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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

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
    @PostMapping(value = "/create")
    public ResponseEntity<Product> createProduct(
            @RequestPart("productRequest") ProductRequest productRequest,
            @RequestPart("imageRequests") MultipartFile imageRequests,
            @RequestHeader(name="Authorization") String token) {

        Product createdProduct;

        System.out.println("PrintProduct: ");
        System.out.println(productRequest);
        System.out.println(imageRequests);

        if (imageRequests != null && !imageRequests.isEmpty()) {
            System.out.println("Create Product with images. ");
            createdProduct = productService.createProduct(productRequest, token);
//            createdProduct = productService.createProductWithImages(productRequest, imageRequests, token);
        } else {
            createdProduct = productService.createProduct(productRequest, token);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @PostMapping(path = "/upload")
    public ResponseEntity<String> uploadFile(
            @RequestPart("name") String productName,
            @RequestPart("description") String productDescription,
            @RequestPart("price") String productPrice,
            @RequestPart(value = "files", required = false) Optional<List<MultipartFile>> imageFilesOptional,
            @RequestHeader(name="Authorization") String token) {

        Product createdProduct;
        ProductRequest productRequest = new ProductRequest(productName, productDescription, productPrice);
        System.out.println(productName);
        System.out.println(productDescription);
        System.out.println(productPrice);
        List<MultipartFile> imageFiles = imageFilesOptional.orElse(Collections.emptyList());

        if ( !imageFiles.isEmpty()) {
            System.out.println("Create Product with images. ");
            for (MultipartFile file : imageFiles) {
                System.out.println("File Name: " + file.getName());
                System.out.println("Original Filename: " + file.getOriginalFilename());
                System.out.println("Content Type: " + file.getContentType());
            }
            createdProduct = productService.createProductWithImages(productRequest, imageFiles, token);
        } else {
            System.out.println("Create Product without images. ");
            createdProduct = productService.createProduct(productRequest, token);
        }



        return imageFiles.isEmpty() ?
                new ResponseEntity<String>(HttpStatus.NOT_FOUND) : new ResponseEntity<String>(HttpStatus.OK);
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
